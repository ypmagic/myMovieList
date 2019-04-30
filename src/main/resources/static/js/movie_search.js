$("#search").submit(function(e) {
  e.preventDefault();
  console.log("hello")
  // TODO: username and password should have requirements (just prevent)
  // submit button if inputs do not meet requirements
  let search = $("search").val();
  let postParameters = {
    search: search 
  };

  console.log(search);
  $.post("/moviesearch", postParameters, responseJSON => {
    let responseObject = JSON.parse(responseJSON);
    console.log("hello2");
  //  if (responseObject.success) {
   //   alert("success");
    //  window.location.replace("/login");
    //} else {
    //  alert("This account already exists.");
   // }
  });
});
