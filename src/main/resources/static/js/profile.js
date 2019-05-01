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
