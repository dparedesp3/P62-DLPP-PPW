
var image = false;

function upload() {
  if (!image) {
    var container = document.getElementById('container');
    var img = document.createElement('img');
    img.src = 'kirby.png';
    container.appendChild(img);
    image = true;
  }
}

function delete_img() {
  var container = document.getElementById('container');
  container.removeChild(container.lastElementChild);
  image = false;
}