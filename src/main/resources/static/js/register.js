$("#register").submit(function(e) {
  e.preventDefault();
  // TODO: username and password should have requirements (just prevent)
  // submit button if inputs do not meet requirements
  let username = $("input[name='username']");
  let password = $("input[password='password']");
  let postParameters = {
    username: username,
    password: password
  };
  $.post("/register", postParameters, responseJSON => {
    let responseObject = JSON.parse(responseJSON);
    if (responseObject.success) {
      window.location.replace("/login");
    } else {
      alert("This account already exists.");
    }
  });
});