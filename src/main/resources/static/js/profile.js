$("#newList").submit(function(e) {
  e.preventDefault();
  let listName = $("input[name='listName']").val();
  let postParameters = {
    listName: listName,
  };
  $.post("/list", postParameters, responseJSON => {
    let responseObject = JSON.parse(responseJSON);
    if (responseObject.success) {
      alert("List added.");
      window.location.reload();
    }
  });
});

$(".remove-buttons").submit(function(e) {
  e.preventDefault();
  let imdbId = this.childNodes[3].value;
  let listId = this.childNodes[3].name;
  let postParameters = {
    movieId: imdbId,
    listId: listId
  };
  console.log(imdbId);
  console.log(listId);
  $.post("/removeFromList", postParameters, responseJSON => {
    let responseObject = JSON.parse(responseJSON);
    if (responseObject.success) {
      window.location.reload();
    }
  });
});
