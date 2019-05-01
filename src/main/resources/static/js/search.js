$("#search-box").on('keyup', function (e) {
    if (e.keyCode == 13) {
        let postParameters = {
          text: this.val()
        }
        // making a post request, sending the value of the search box to
        // back end
        $.post("/loginattempt", postParameters, responseJSON => {
          let responseObject = JSON.parse(responseJSON);
          // do stuff here
        });
    }
});
