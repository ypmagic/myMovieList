// THIS IS ALL MENU STUFF
var toggleHam = document.querySelector(".toggle-menu-ham");
var toggleX = document.querySelector(".toggle-menu-x");
var mobileMenu = document.querySelector(".menu");
var body = document.querySelector("body");
var greySite = document.querySelector(".mobile-overlay")
var logo = document.querySelector (".logo");

/*
MOBILE MENU STUFF
*/
function hamburgerToggle() {
  toggleHam.addEventListener("click", hamburgerStuff);
}

function hamburgerStuff() {
  this.style.display = "none";
  toggleX.style.display = "block";
  mobileMenu.className += " menu-animate-height";
  document.querySelector("body").className += " stop-scrolling";
  greySite.style.display = "block";
  logo.style.borderBottom = "none";
}
hamburgerToggle();

toggleX.addEventListener("click", function() {
  this.style.display = "none";
  toggleHam.style.display = "block";
  mobileMenu.className = "menu";
  document.querySelector("body").className = "";
  greySite.style.display = "none";
  logo.style.borderBottom = "5px solid rgb(93, 186, 237)";
});

$("#signout").onclick = function(e) {
  e.preventDefault();
  console.log("hello");
};