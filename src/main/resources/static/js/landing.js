/*
MOVIE INFO BOXES
*/
let movies = document.getElementsByClassName("movie-img");
for (let j = 0; j < movies.length; j++) {
  movies[j].addEventListener("click", function() {
    let imdbId = this.classList[1];
    console.log(imdbId);
    let infoBox = document.querySelector("#" + imdbId);
    let greySite = document.querySelector(".mobile-overlay")
    infoBox.style.display = "block";
    infoBox.style.opacity = "1";
    greySite.style.display = "block";
    document.querySelector("body").className += "stop-scrolling";
    // exit button
    let exitInfo = infoBox.childNodes[1].childNodes[1];
    exitInfo.addEventListener("click", function() {
      let tempMember = this.parentElement.parentElement;
      tempMember.style.opacity = "0";
      tempMember.style.display = "none";
      greySite.style.display = "none";
      document.querySelector("body").className = "";
    });
  })
}

$(".formlst").submit(function(e) {
  e.preventDefault();
  let selectVar = this.childNodes[1].childNodes[1];
  let listId = selectVar.value;
  let listName = selectVar[selectVar.selectedIndex].innerHTML;
  let imdbId = selectVar.getAttribute("name");
  console.log(listId);
  console.log(imdbId);
  let postParameters = {
    listName: listId,
    movieId: imdbId
  };
  $.post("/addToList", postParameters, responseJSON => {
    let responseObject = JSON.parse(responseJSON);
    if (responseObject.success) {
      alert("Added this movie to list: " + listName);
    }
  });
});
