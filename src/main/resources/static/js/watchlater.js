$(".remove-watchlater").submit(function(e) {
  e.preventDefault();
  let imdbId = this.childNodes[3].value;
  let postParameters = {
    movieId: imdbId,
  };
  $.post("/removeWatchLater", postParameters, responseJSON => {
    let responseObject = JSON.parse(responseJSON);
    if (responseObject.success) {
      window.location.reload();
    }
  });
});