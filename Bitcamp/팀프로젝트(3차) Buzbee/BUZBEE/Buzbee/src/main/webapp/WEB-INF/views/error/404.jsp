<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 NOT FOUND</title>

<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/web.ico" type="image/x-icon">

<style>
body{
    margin : 0;
    padding : 0;
    overflow: hidden;
  }

  #container canvas, .man {
  position: absolute;
}

#man1{
  top: 50%;
  left: 25%;
  transform: scale(0.5);
}

.man {
  width: 200px;
  margin-top: 0px;
  height: 360px;
}

.head {
  width: 70px;
  height: 70px;
  background-color: #444;
  border-radius: 50%;
  animation-name: body;
  animation-duration: 4s;
  animation-iteration-count: infinite;
  animation-timing-function: ease-in-out;
}

.body {
  height: 144px;
  width: 62px;
  background-color: #444;
  border-radius: 100% 20% 0 0;
  transform: skewX(-2deg);
  position: relative;
  left: -50px;
  animation-name: body;
  animation-duration: 4s;
  animation-iteration-count: infinite;
  animation-timing-function: ease-in-out;
}

.foot {
  width: 40px;
  height: 14px;
  border-radius: 10px 80% 4px 4px;
  transform: skewX(5deg);
  background-color: #444;
  display: inline-block;
  animation-name: foot;
  animation-duration: 1.8s;
  animation-iteration-count: infinite;
  animation-timing-function: ease-in-out;
}
.foot:last-child {
  margin-left: -40px;
  animation-delay: .9s;
}

.feet {
  text-align: left;
  position: relative;
  top: 100px;
  left: -66px;

  &:before {
    width: 120px;
    height: 8px;
    border-radius: 50%;
    content: "";
    background: rgba(#000,.1);
    position: absolute;
    bottom: -2px;
    left: -16px;
    animation-name: shadow;
    animation-duration: 3s;
    animation-iteration-count: infinite;
    animation-timing-function: ease-in-out;
  }
}

@keyframes foot {
  20% {
    transform: translate3d(34px, -16px, 0) rotate(4deg);
  }
  26% {
      transform: translate3d(34px, -10px, 4) rotate(0deg);
  }
  40% {
      transform: translate3d(56px, -6px, 0) rotate(-6deg);
  }
  44% {
      transform: translate3d(56px, 0, 0) rotate(0deg);
  }

}

@keyframes body {
  25% {
    transform: translateY(5px);
  }
  50% {
    transform: translateY(0);
  }
  75% {
    transform: translateY(5px);
  }
}


@keyframes shadow {
  25% {
    transform: scale(1.1);
  }

  75% {
    transform: scale(.96);
  }
}
</style>
</head>
<body>
<!-- click on the walking man to know what's in his head -->
<div id="container">
  <canvas id="canvas"></canvas>
  <div class="man" id="man1">
    <div class="head"></div>
    <div class="body"></div>
    <div class="feet">
      <div class="foot"></div>
      <div class="foot"></div>
    </div>
  </div>
</div>
<script>
"use strict";
///// GLOBALS ***************************************************//
var W=window.innerWidth,H=window.innerHeight;
var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
var dashedRain = false;
var rainIntensity = 1;
var maxRadius = 2;
var speed = 5;
var textMaxSpeed = -0.5;
var gravity =0.3;
var airResistance = 1;
var particleTTL = 25;
var randomSpawnRate = 75;
var wind = 0;
var ticker = 0;
var circleArr;
var miniCircleArr;
var startDate = new Date();
var endDate = new Date();
var rainInc = true;
var windInc = true;
var gravityInc = true;
var timeControl = 0.7;
var clr = 34;
var timeDiff = 0;
var firstLight = true;
var background = "linear-gradient(#333, #111)";
var words = [];
var showThoughts = false;
///// GLOBALS ***************************************************//

//Man click event
showThoughts = !showThoughts;

//Responsiveness
window.addEventListener("resize",function(){
W = window.innerWidth;
H = window.innerHeight;
canvas.width = W;
canvas.height = H;
document.getElementById("man1").style.top = H-(300-44) + "px";

// start(true);
});

//Lightning effect
function lightning(){
clr = 125;
}

function drawBuilding(h,n,color){
var y = H-h;
var w = W/n+1;
var gap = w/n+1;
var h = h;
var x =gap;

for(var v = 0;v<n;v++){
var grd = ctx.createLinearGradient(x+w/2,y, x+w/2, y+h);
grd.addColorStop(0, color);
grd.addColorStop(1, "#111");

ctx.rect(x,y,w,h);
ctx.fillStyle = grd;
ctx.fill();
x += w +gap;
}

}

//util
function getRandomInt(min, max) {
min = Math.ceil(min);
max = Math.floor(max);
return Math.floor(Math.random() * (max - min)) + min; //The maximum is exclusive and the minimum is inclusive
}
//Responsiveness
canvas.addEventListener("resize",function(){
W=window.innerWidth;
H=window.innerHeight;
start(true);
});


//words
var Word = function(string){
this.string = string.toUpperCase();
this.speed = Math.random()*textMaxSpeed;
this.size = getRandomInt(10,27);
this.x = Math.random()*W;
this.y = getRandomInt(H/3,H-H/2.5);
}
Word.prototype.draw = function(){
ctx.font =  this.size+"px impact";
ctx.fillStyle = "#444";
ctx.fillText(this.string, this.x, this.y);
}
Word.prototype.update = function(){
this.x += this.speed;
if(this.x<-100){
this.x = W+100;
}
this.draw();
}

//Circle Class
var Circle = function(x,y,r,dx,dy){
this.x=x;
this.y=y;
this.r=r;
this.velocity = {
x:dx,
y:dy
};
};
Circle.prototype.draw = function(){
ctx.beginPath();
if(dashedRain){
ctx.moveTo(this.x, this.y);
ctx.lineTo(this.x+wind, this.y+getRandomInt(1,10));
ctx.lineWidth = "0.5";
ctx.strokeStyle = "#aaa";
}else{
ctx.arc(this.x,this.y,this.r,Math.PI*2,false);
ctx.strokeStyle = "#fff";
}
ctx.closePath();

ctx.fillStyle = "#444";
ctx.stroke();
ctx.fill();
}
Circle.prototype.update = function(){
// boundary
// if(this.x-this.r<-W/2){
//   this.velocity.x = -this.velocity.x;
// }
if(this.x+this.r>W*3){
// this.velocity.x = -this.velocity.x;
}else{
this.velocity.x += wind/10;
}
// if(this.y-this.r<0){this.velocity.y = -this.velocity.y;}

//lower boundary + gravity
if(this.y+this.r>H){
this.r -= this.r;
this.velocity.y = -this.velocity.y * airResistance;
this.shatter();
// delete this;
}else{
this.velocity.y += gravity;
}

this.x +=this.velocity.x;
this.y +=this.velocity.y;
////
this.draw();
}
Circle.prototype.shatter = function(){
for(var v=0;v<8;v++){
miniCircleArr.push(new MiniCircle(this.x,this.y,1));
}
}

//Mini Circles
var MiniCircle = function(x,y,r){
Circle.call(this,x,y,r);
this.velocity = {
x: getRandomInt(-5,5),
y: getRandomInt(-15,15)
};
this.ttl = particleTTL;
this.opacity = 1;
}
MiniCircle.prototype.draw = function(){
ctx.beginPath();
ctx.arc(this.x,this.y,this.r,Math.PI*2,false);
ctx.closePath();
// ctx.stroke();
ctx.fillStyle = "rgba(255, 255, 255, "+this.opacity+")";
ctx.fill();
}
MiniCircle.prototype.update = function(){
// boundary
if(this.x-this.r<0||this.x+this.r>W){this.velocity.x = -this.velocity.x;}
if(this.y-this.r<0){this.velocity.y = -this.velocity.y;}

//lower boundary + gravity
if(this.y+this.r>=H){
this.velocity.y = -this.velocity.y * airResistance;
}else{
this.velocity.y += gravity;
}

this.x +=this.velocity.x;
this.y +=this.velocity.y;
this.ttl -= 1;
this.opacity -= 1/this.ttl;
////
this.draw();
}



///// START AND UPDATE ******************************************//
//start
function start(refresh = false){
// canvas.style.background = "#222";
canvas.style.background = background;
canvas.width = W;
canvas.height = H;
ctx.lineWidth="1";
ctx.strokeStyle="#fff";
ctx.fillStyle ="#444";
document.getElementById("man1").style.top = H-(300-44) + "px";

circleArr = [];
miniCircleArr = [];

setTimeout(lightning,4000);
setTimeout(lightning,4200);

words.push(new Word("404"));
words.push(new Word("PAGE NOT FOUND"));
words.push(new Word("페이지를 찾을 수 없음..."));
words.push(new Word("404"));
words.push(new Word("PAGE NOT FOUND"));
words.push(new Word("404"));
words.push(new Word("404"));
words.push(new Word("코딩하기 싫다....."));
words.push(new Word("페이지를 찾을 수 없음...?"));
words.push(new Word("404"));
words.push(new Word("PAGE NOT FOUND"));
words.push(new Word("PAGE NOT FOUND"));
words.push(new Word("無念無想"));
words.push(new Word("404"));
words.push(new Word("트위터 괜히 했어..."));
words.push(new Word("PAGE NOT FOUND"));
words.push(new Word("404"));
words.push(new Word("어떤거요?"));
words.push(new Word("두마디잖아요..."));


if(!refresh){
// for(var v=0;v<250;v++){
//   var r = Math.random() * maxRadius;
//   var x = Math.random() * (W-r*2)+r;
//   var y = Math.random() * (H-r*2)+r;
//   var dx = (Math.random() - 0.5) * speed;
//   var dy = (Math.random() - 0.5) * speed;
//   circleArr.push(new Circle(x,y,r,dx,dy));
// }
////
update();
}
}

//Update every frame
function update(){
requestAnimationFrame(update);
ctx.clearRect(0,0,W,H);
////

// draw building
drawBuilding(H/1.2, 4,"#333");
// draw words
if(showThoughts){
for(var v=0;v<words.length;v++){
  words[v].update();
}
}


for(var v=0;v<circleArr.length;v++){
if(circleArr[v].r>= 0){
  circleArr[v].update();
  if(circleArr[v].r ==0){
    circleArr.splice(v,1);
  }
}
}
for(var v=0;v<miniCircleArr.length;v++){
if(miniCircleArr[v]!== undefined){
  miniCircleArr[v].update();
  if(miniCircleArr[v].ttl == 0){
    miniCircleArr.splice(v,1);
  }
}
}

// if(ticker%randomSpawnRate == 0){
for(var v=0;v<rainIntensity;v++){
  var r = Math.random() * maxRadius;
  var x=Math.random()*(W*3)-30;
  var y = 0;
  // var dx = (Math.random() - 0.5) * speed;
  var dx = wind;
  var dy = (Math.random() - 0.5) * speed;
  circleArr.push(new Circle(x,y,r,dx,dy));
}
// }

// wind control

endDate = new Date();
timeDiff = (endDate - startDate)/1000;
if(timeDiff > timeControl){
if(wind>=-6 && windInc){
  wind-=0.5;

  if (wind<=-6) {
    windInc = false;
    setTimeout(lightning,getRandomInt(1000,4000));
  }
}else{
  wind+=0.5;
  if(wind>0){
    windInc = true;
    setTimeout(lightning,getRandomInt(1000,4000));
   }
}

if(wind<-3){
  timeControl =0.5;
  rainIntensity = 3;
}else if(wind>=-3 && wind<-1){
  timeControl = 0.1;
  rainIntensity =1;
}else{
  timeControl = 4;
  rainIntensity =6;
}

startDate = endDate;
}

//Lightning
if(clr>51){
console.log("lightning");
canvas.style.background = "linear-gradient(rgb("+clr+","+clr+","+clr+"),#111)";
clr-= 2;
}else{
canvas.style.background = background;
}
}

///// MAIN ******************************************************//
start();
///// MAIN ******************************************************//

</script>
</body>
</html>