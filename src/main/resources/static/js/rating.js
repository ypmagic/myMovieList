$("#ratingForm").submit(function(e) {
  e.preventDefault();
  let rating = $("#rating1").val();
  let id = document.querySelector("#movie-id").classList[0];
  let postParameters = {
    rating: rating,
    id: id
  };
  $.post("/insertrating", postParameters, responseJSON => {
    let responseObject = JSON.parse(responseJSON);
    if (responseObject.success) {
      window.location.reload();
    }
  });
});

$(".remove-rated").submit(function(e) {
  e.preventDefault();
  let imdbId = this.childNodes[3].value;
  console.log(imdbId);
  let postParameters = {
    movieId: imdbId,
  };

  $.post("/removeRatedMovie", postParameters, responseJSON => {
    let responseObject = JSON.parse(responseJSON);
    if (responseObject.success) {
      window.location.reload();
    }
  });
});

$(".ratingChange").submit(function(e) {
  e.preventDefault();
  let rating = this.childNodes[1].value;
  let id = this.childNodes[3].value;

  let postParameters = {
    rating: rating,
    id: id
  };
  $.post("/insertrating", postParameters, responseJSON => {
    let responseObject = JSON.parse(responseJSON);
    if (responseObject.success) {
      window.location.reload();
    }
  });
});
