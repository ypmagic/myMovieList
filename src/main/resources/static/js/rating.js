$("#ratingForm").submit(function(e) {
  e.preventDefault();
  console.log('hi')
  let rating = $("#rating1").val();
  let id = document.querySelector("#movie-id").classList[0];
  console.log(rating)
  console.log(id)
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
