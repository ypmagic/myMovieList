<!DOCTYPE html>
  <head>
    <meta charset="utf-8">
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- In real-world webapps, css is usually minified and
         concatenated. Here, separate normalize from our code, and
         avoid minification for clarity. -->
    <link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" href="/css/html5bp.css">
    <link rel="stylesheet" href="/css/landing.css">
    <link rel="stylesheet" href="/css/main.css">
  </head>
  <body>

     ${content}

     <!-- Again, we're serving up the unminified source for clarity. -->
     <script src="/js/jquery-2.1.1.js"></script>
     <script src="/js/jquery-3.1.1.js"></script>
     <script src="/js/register.js"></script>
     <script src="/js/login.js"></script>
     <script src="/js/landing.js"></script>

    <div class="tab">
      <form method = "GET" action="/stars">
      <button type="GET" value="Stars">MyMovieList</button>
      </form>
      <form style="float:right" method = "GET" action="/autocorrect">
      <button type="GET" value="Autocorrect">Watch Later</button>
    </form>
    <form style="float:right" method = "GET" action="/bacon">
    <button type="GET" value="Bacon">Settings</button>
    </form>
        <form style="float:right" method = "GET" action="/bacon">
    <button type="GET" value="Bacon">User</button>
    </form>
    </div>
     <!-- Bootstrap! -->
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  </body>
  <!-- See http://html5boilerplate.com/ for a good place to start
       dealing with real world issues like old browsers.  -->
</html>
