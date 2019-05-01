$("#search").on('keyup', function (e) {
  e.preventDefault();
  if (e.keyCode == 13) {
    $("#searchForm").submit();
    console.log("lmao")
    }
  console.log("hello")
  // TODO: username and password should have requirements (just prevent)
  // submit button if inputs do not meet requirements
});
