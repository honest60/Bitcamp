$likeButton = document.getElementById('like')

$likeButton.addEventListener('click', () => {
  $likeButton.classList.toggle('is-liked')
})