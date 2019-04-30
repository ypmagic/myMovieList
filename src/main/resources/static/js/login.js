$("#login").submit(function(e) {
  e.preventDefault();
  let username = $("input[name='username']").val();
  let password = $("input[name='password']").val();
  let postParameters = {
    username: username,
    password: password
  };
  $.post("/loginattempt", postParameters, responseJSON => {
    let responseObject = JSON.parse(responseJSON);
    if (responseObject.success) {
      window.location.replace("/home");
    } else {
      alert("Wrong password, or invalid login.")
    }
  });
});
