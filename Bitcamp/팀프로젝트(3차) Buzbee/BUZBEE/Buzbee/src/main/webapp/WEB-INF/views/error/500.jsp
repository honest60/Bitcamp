<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% response.setStatus(200); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500 INTERNAL SERVER ERROR</title>

<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/web.ico" type="image/x-icon">

<style>
@import url("https://fonts.googleapis.com/css?family=Roboto+Condensed|Playfair+Display:700i|Didact+Gothic");
html,
body {
  overflow: hidden;
  background: black;
  font-family: monospace;
  font-family: "Roboto", sans-serif;
}
html {
  box-sizing: border-box;
}
*,
*:before,
*:after {
  box-sizing: inherit;
}
* {
  margin: 0;
  padding: 0;
}
h1 {
  font-family: "Roboto Condensed", "Helvetica Neue", Helvetica, Arial,
    sans-serif;
  font-size: 30px;
  line-height: 1;
}
h3 {
  font-weight: lighter;
}

section {
  color: white;
  max-width: 275px;
  width: 100%;
  display: none;
  font-size:14px;
}
p {
  color: rgba(255, 255, 255, 0.5);
}
a {
  color: white;
}

span.low {
  color: red;
}
.btn-group {
  display: flex;
  -webkit-display: flex;
  justify-content: space-between;
  -webkit-justify-content: space-between;
  width: 100%;
  padding: 50px 25px;
  position: absolute;
  left: 0;
  bottom: 0;
  visibility: hidden;
}
.btn-group span {
  width: 75px;
  height: 75px;
  border-radius: 50%;
  background: white;
  background: #222;
  color: white;
  text-align: center;
  display: flex;
  -webkit-display: flex;
  align-items: center;
  -webkit-align-items: center;
  justify-content: center;
  -webkit-justify-content: center;
  font-weight: bold;
  font-size: 30px;
  box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.5);
}

#VR {
  position: fixed;
  top: 20px;
  right: 20px;
  outline: none;
  border: none;
  width: 60px;
  height: 40px;
  z-index: 100;
  background: rgba(90, 0, 0, 0.75);
  display: flex;
  -webkit-display: flex;
  justify-content: center;
  -webkit-justify-content: center;
  align-items: center;
  -webkit-align-items: center;
  visibility: hidden;
}

#VR svg {
  width: 60px;
  height: 35px;
  transform: scale(2);
  -webkit-transform: scale(2);
  fill: white;
}
button {
  background: none;
}

button:hover {
  cursor: pointer;
}

@media (min-width: 1100px) {
  section {
    display: block;
    position: absolute;
    bottom: 50px;
    left: 50px;
  }
}

/* Show only on touchscreen devices */
@media (pointer: coarse) {
  .btn-group,
  #VR {
    visibility: visible;
  }
  .hide {
    visibility: hidden;
  }
}

</style>
</head>
<body>
  <button id='VR' class='button toggleVR' onclick='toggleVR()' title='Toggle VR Mode for Mobile Devices Only'>
    <svg id="Layer_1" xmlns="http://www.w3.org/2000/svg" viewBox="-12 -12 48 48"><path fill="#fff" d="M23.058 5.038H.942c-.522 0-.942.422-.942.94V18.02c0 .522.423.942.942.942H8.8c.26 0 .492-.174.564-.425.88-3.028 1.062-4.754 2.724-4.754 1.66 0 1.905 1.973 2.685 4.766.068.243.29.41.543.41h7.743c.52 0 .94-.424.94-.943V5.982c0-.522-.42-.944-.942-.944zm-16.43 9.467c-1.384 0-2.504-1.12-2.504-2.504s1.12-2.503 2.504-2.503 2.5 1.12 2.5 2.504-1.12 2.505-2.5 2.505zm10.744 0c-1.384 0-2.504-1.12-2.504-2.504s1.12-2.503 2.504-2.503c1.383 0 2.504 1.12 2.504 2.504s-1.12 2.505-2.504 2.505z"/></svg>
  </button>

  <section>
    <h1><span class="low">Internal Server ERROR</span> 500:</h1>
    <p>웹 서버가 요청사항을 수행할 수 없음</p>
</section>


  <div class="btn-group">
    <span>RL</span>
    <span>RR</span>
  </div>


<script src="https://threejs.org/build/three.min.js"></script>
<script src="https://threejs.org/examples/js/loaders/deprecated/LegacyJSONLoader.js"></script>
<script src="https://threejs.org/examples/js/loaders/GLTFLoader.js"></script>
<script src="https://threejs.org/examples/js/controls/DeviceOrientationControls.js"></script>
<script src="https://threejs.org/examples/js/effects/StereoEffect.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.11.2/TweenMax.min.js"></script>
<script>
const randnum = (min, max) => Math.round(Math.random() * (max - min) + min);

//=========================================================================================== scene
var scene = new THREE.Scene();

//=========================================================================================== camera
var camera = new THREE.PerspectiveCamera( 45, window.innerWidth / window.innerHeight, 1, 10000 );


//=========================================================================================== canvas
renderer = new THREE.WebGLRenderer({
alpha: true,
antialias: true
});
renderer.setSize(window.innerWidth, window.innerHeight);
renderer.shadowMapEnabled = true; //Shadow
renderer.shadowMapSoft = true; // Shadow
renderer.shadowMapType = THREE.PCFShadowMap; //Shadow
document.body.appendChild(renderer.domElement);

//=========================================================================================== add VR
renderer.setPixelRatio(window.devicePixelRatio); //VR
var effect = new THREE.StereoEffect(renderer); //VR
effect.setSize(window.innerWidth, window.innerHeight); //VR
var VR = false;

function toggleVR() {
if (VR) {
  VR = false;
  camera.rotation.reorder( 'ZYX' );
  camera.lookAt(0,0,0);
} else {
  VR = true;
  controls = new THREE.DeviceOrientationControls(camera);
  requestFullscreen(document.documentElement);
}
renderer.setSize(window.innerWidth, window.innerHeight);
}

//=========================================================================================== resize
window.addEventListener("resize", function() {
let width = window.innerWidth;
let height = window.innerHeight;
renderer.setSize(width, height);
camera.aspect = width / height;
camera.updateProjectionMatrix();
});


//=========================================================================================== fog
scene.fog = new THREE.FogExp2(new THREE.Color("lightblue"), 0.005);

//=========================================================================================== light
var spotLight = new THREE.SpotLight(new THREE.Color('white'), .15);
spotLight.position.set(5, 100, 0);
spotLight.castShadow = true;
scene.add(spotLight);



var sphereLight = new THREE.SphereGeometry(.05);
var sphereLightMaterial = new THREE.MeshBasicMaterial({
color: new THREE.Color("white")
});
var sphereLightMesh = new THREE.Mesh(sphereLight, sphereLightMaterial);
sphereLightMesh.castShadow = true;
sphereLightMesh.position.set(0,2.5,0)
//scene.add(sphereLightMesh);


var distance = 10;
var intensity = 2.5;

var pointLight2 = new THREE.PointLight(new THREE.Color('red'), intensity, distance);
pointLight2.position.set(0, 0, -5);
scene.add(pointLight2);


var pointLight3 = new THREE.PointLight(new THREE.Color('#808000'), intensity, distance);
pointLight3.position.set(0, 0, 5);
scene.add(pointLight3);




//=========================================================================================== floor
THREE.ImageUtils.crossOrigin = '';
var floorMap = THREE.ImageUtils.loadTexture("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTG76Ug6KATmlUnoLoZV8xJFLK2zGo3Jvw7xqyFSd3iBUue7PZ3");
floorMap.wrapS = floorMap.wrapT = THREE.RepeatWrapping;
floorMap.repeat.set(50, 50);

var groundMaterial = new THREE.MeshPhongMaterial({
color: new THREE.Color('#111'),
specular: new THREE.Color('#808000'),
//specular: new THREE.Color('#333'),
shininess: 0,
bumpMap: floorMap
});
var groundGeo = new THREE.PlaneGeometry(200, 200);
var ground = new THREE.Mesh(groundGeo, groundMaterial);


ground.position.set(0, 0, 0);
ground.rotation.x = (-Math.PI / 2);
ground.receiveShadow = true;
scene.add(ground);



//===================================================== add tree
//3D Model from http://www.sweethome3d.com/searchModels.jsp?model=tree&x=0&y=0
var loader = new THREE.LegacyJSONLoader();
loader.load("https://raw.githubusercontent.com/baronwatts/models/master/real-tree2.js", function(geometry, materials) {

new Array(100).fill(null).map( (d, i) => {
  x = Math.cos(i/100 * Math.PI * 2) * randnum(7,50);
  z = Math.sin(i/100 * Math.PI * 2) * randnum(7,50);
  y = -1;

  var obj = new THREE.Mesh(geometry, materials);
  obj.scale.set(5, 5, 5);
  obj.castShadow = true;
  obj.position.set(x, y, z);
  scene.add(obj);
});


});



//===================================================== add model
if (window.innerWidth > 768) {
var leafs = [];
loader = new THREE.LegacyJSONLoader();
loader.load('https://raw.githubusercontent.com/baronwatts/models/master/single-leaf.js', function(geometry, materials) {

  //create leafs
  new Array(250).fill(null).map( (d, i) => {
    var matt = new THREE.MeshLambertMaterial({
      transparent: true,
      opacity: 1,
      side: THREE.DoubleSide,
      color: new THREE.Color('silver')
    });
    var particle = new THREE.Mesh(geometry, matt);
    particle.position.set(randnum(-10, randnum(10, 20)), 20, randnum(-10, 10));
    particle.scale.set(.5, .5, .5);
    particle.rotateY(Math.random() * 180);
    scene.add(particle);
    leafs.push(particle);
  });

  leafs.map((d, i) => {
    //position
    if (i % 2 == 0) {
      leafs[i].position.y = 0;
    } else {
      TweenMax.to(leafs[i].position, 10, {
        y: 0,
        x: randnum(-10, 10),
        ease: Power2.Linear,
        delay: 0.025 * i,
        repeat: -1
      }, 1);
    }
    //rotation
    if (i % 2 == 0) {
      leafs[i].rotation.y = 0;
    } else {
      TweenMax.to(leafs[i], 5, {
        rotationY: '+=25',
        ease: Power2.Linear,
        delay: 0.025 * i,
        repeat: -1
      }, 1);
    }

  }); //end leafs

});

} //end if







//===================================================== models
var mixers = [];
var characterGroup = new THREE.Group();
scene.add(characterGroup);

loadModels();
function loadModels() {
const loader = new THREE.GLTFLoader();

loader.load( 'https://raw.githubusercontent.com/baronwatts/models/master/Reindeer.glb', function(gltf){
    var model = gltf.scene.children[ 0 ];
    model.position.set( 0, 0, -5 );
    model.scale.set( .025, .025, .025 );
    model.rotateZ(Math.PI);

    var animation = gltf.animations[ 0 ];
    var mixer = new THREE.AnimationMixer( model );
    mixers.push( mixer );

    var action = mixer.clipAction( animation );
    action.play();

    gltf.scene.traverse(function(node) {
      if (node instanceof THREE.Mesh) {
        node.castShadow = true;
        node.material.side = THREE.DoubleSide;
      }
    });
    scene.add( model );
});


loader.load( 'https://raw.githubusercontent.com/baronwatts/models/master/Reindeer.glb', function(gltf){
    var model = gltf.scene.children[ 0 ];
    model.position.set( 10, 0, 0 );
    model.scale.set( .025, .025, .025 );
    model.rotateZ(-Math.PI/2);

    var animation = gltf.animations[ 2 ];
    var mixer = new THREE.AnimationMixer( model );
    mixers.push( mixer );

    var action = mixer.clipAction( animation );
    action.play();

    gltf.scene.traverse(function(node) {
      if (node instanceof THREE.Mesh) {
        node.castShadow = true;
        node.material.side = THREE.DoubleSide;
      }
    });
    characterGroup.add( model );
});



}//end loadModels




//=========================================================================================== model
loader = new THREE.LegacyJSONLoader();
loader.load('https://raw.githubusercontent.com/baronwatts/models/master/camp.js', function(geometry, materials) {
  var matt = new THREE.MeshLambertMaterial({
    vertexColors: THREE.FaceColors,
    transparent: true,
    opacity: 1,
    side: THREE.DoubleSide
  });
  var wall = new THREE.Mesh(geometry, matt);
  wall.position.set(0, 0, 0);
  wall.rotateY(Math.PI);
  wall.scale.set(4, 4, 4);
  scene.add(wall);
});



//=========================================================================================== full screen
var requestFullscreen = function(ele) {
if (ele.requestFullscreen) {
  ele.requestFullscreen();
} else if (ele.webkitRequestFullscreen) {
  ele.webkitRequestFullscreen();
} else if (ele.mozRequestFullScreen) {
  ele.mozRequestFullScreen();
} else if (ele.msRequestFullscreen) {
  ele.msRequestFullscreen();
} else {
  console.log('Fullscreen API is not supported.');
}
}
var exitFullscreen = function(ele) {
if (ele.exitFullscreen) {
  ele.exitFullscreen();
} else if (ele.webkitExitFullscreen) {
  ele.webkitExitFullscreen();
} else if (ele.mozCancelFullScreen) {
  ele.mozCancelFullScreen();
} else if (ele.msExitFullscreen) {
  ele.msExitFullscreen();
} else {
  console.log('Fullscreen API is not supported.');
}
}


//=========================================================================================== add tweening
//https://greensock.com/forums/topic/16993-threejs-properties/
Object.defineProperties(THREE.Object3D.prototype, {
x: {
  get: function() {
    return this.position.x;
  },
  set: function(v) {
    this.position.x = v;
  }
},
y: {
  get: function() {
    return this.position.y;
  },
  set: function(v) {
    this.position.y = v;
  }
},
z: {
  get: function() {
    return this.position.z;
  },
  set: function(v) {
    this.position.z = v;
  }
},
rotationZ: {
  get: function() {
    return this.rotation.x;
  },
  set: function(v) {
    this.rotation.x = v;
  }
},
rotationY: {
  get: function() {
    return this.rotation.y;
  },
  set: function(v) {
    this.rotation.y = v;
  }
},
rotationX: {
  get: function() {
    return this.rotation.z;
  },
  set: function(v) {
    this.rotation.z = v;
  }
}
});





//=========================================================================================== add Animation
let angle = 0,
lastTime = null,
u_frame = 0,
clock = new THREE.Clock(),
count = 0,
prevTime = Date.now(),
phase = 0;


function moveCharacter(){
  characterGroup.position.z < -75 ? (characterGroup.position.z = 75) : (characterGroup.position.z -= .50);
}

function moveLights(){
phase += 0.03;
sphereLightMesh.position.z = 5 - Math.cos(phase) * 5;
sphereLightMesh.position.x = Math.sin(phase) * 5;
pointLight3.position.copy(sphereLightMesh.position);

}
 


//===================================================== mouse
var mouseX = 0;
var mouseY = 0;
var zoomIn = 20;
document.addEventListener( 'mousemove', onDocumentMouseMove, false );
function onDocumentMouseMove(event) {
mouseX = ( event.clientX - window.innerWidth / 2 ) / zoomIn;
mouseY = ( event.clientY - window.innerHeight  / 2 ) / zoomIn;
}


(function animate() {
//update models
const delta = clock.getDelta();
mixers.forEach( ( mixer ) => { mixer.update( delta * 1.25 ); } );
moveCharacter();
moveLights();


//VR Mode
if(VR){
   effect.render(scene, camera);
   controls.update();
   document.querySelector('.btn-group').classList.add('hide');
}else{
    renderer.render(scene, camera);
    camera.position.x += ( mouseX - camera.position.x ) * .05;
    camera.lookAt( scene.position );
    document.querySelector('.btn-group').classList.remove('hide');
}

requestAnimationFrame(animate);

})();



//set camera position
camera.position.y = 1.5;
camera.position.z = -25;
camera.position.x = 50;
</script>
</body>
</html>