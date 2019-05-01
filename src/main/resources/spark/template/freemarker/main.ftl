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
    <link rel="stylesheet" href="/css/register.css">
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="/css/profile.css">
    <link rel="stylesheet" href="/css/movies.css">
  </head>
  <body>
    <div class="nav">
        <div class="logo">
          <span class="toggle-menu toggle-menu-ham">
            <span class="hamburger hamburger-nav"></span>
          </span>
          <span class="toggle-menu toggle-menu-x">
            <span class="x x-nav"></span>
          </span>
          <div class="logo-text-mob">
            <h3>
              MyMovieList
            </h3>
          </div>
          <img src="https://i.ibb.co/9Hm8MFy/logo.jpg" alt="Logo" id="logo">
        </div>
        <!-- Dropdown or slide in from left? Take up full screen? idk -->
        <ul class="menu">
            <li><a href="/home" class="boldless-link">Home</a></li>
            <li><a href="/profile" class="boldless-link">Profile</a></li>
            <li><a href="/watchlater" class="boldless-link">Watch Later</a></li>
            <li><a href="/lists" class="boldless-link">My Lists</a></li>
        </ul>
      </div>
    </div>
    <div class="search">
      <form method="POST" action="/moviesearch" id="searchForm">
      <input class="search-box" type="text" placeholder="Search.." name="search" id="search-box">
    </form>
    </div>

     ${content}

     <!-- Again, we're serving up the unminified source for clarity. -->
     <script src="/js/jquery-2.1.1.js"></script>
     <script src="/js/jquery-3.1.1.js"></script>
     <script src="/js/register.js"></script>
     <script src="/js/login.js"></script>
     <script src="/js/landing.js"></script>
     <script src="/js/main.js"></script>
     <script src="js/profile.js"></script>
     <script src="js/movie_search.js"></script>
     <!-- Bootstrap! -->
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  </body>
  <!-- See http://html5boilerplate.com/ for a good place to start
       dealing with real world issues like old browsers.  -->
</html>
