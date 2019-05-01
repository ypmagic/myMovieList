$("#search").on('keyup', function (e) {
  e.preventDefault();
  if (e.keyCode == 13) {
    let search = $("search-box").val();
    console.log(search)
    let postParameters = {
      search: search 
    };
        // making a post request, sending the value of the search box to
        // back end
    $.post("/moviesearch", postParameters, responseJSON => {
          let responseObject = JSON.parse(responseJSON);
          console.log("hello2");
          if (len(responseJSON.movies) == 0) {
            console.log("NO MOVIES FOUND");
          } else {
            for (movie in responseJSON.movies) {
              console.log(movie.title);
            }
          }
        });
    }
  console.log("hello")
  // TODO: username and password should have requirements (just prevent)
  // submit button if inputs do not meet requirements
});
