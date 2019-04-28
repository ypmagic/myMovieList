$("#login").submit(function(e) {
  let username = $("input[name='username']");
  let password = $("input[password='password']");
  let postParameters = {
    username: username,
    password: password
  };
  $.post("/register", postParameters, responseJSON => {
    let responseObject = JSON.parse(responseJSON);
    if (responseObject.success) {
      alert("success!")
    } else {
      alert("wrong password, or invalid login")
    }
  });
});
