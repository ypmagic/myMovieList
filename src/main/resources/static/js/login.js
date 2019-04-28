$("#login").submit(function(e) {
  e.preventDefault();
  let username = $("input[name='username']").val();
  let password = $("input[password='password']").val();
  let postParameters = {
    username: username,
    password: password
  };
  $.post("/loginattempt", postParameters, responseJSON => {
    let responseObject = JSON.parse(responseJSON);
    if (responseObject.success) {
      alert("success!")
    } else {
      alert("wrong password, or invalid login")
    }
  });
});
