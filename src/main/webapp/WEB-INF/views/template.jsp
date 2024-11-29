<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <!-- Latest compiled and minified CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- Latest compiled JavaScript -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

    <div class = "bg-primary text-white text-center p-5">
        <h1>MetaBuild Home</h1>
        <p>Welcome to Home</p>

    </div>
    <!-- ------------------------------------------------------------>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
      <div class="container-fluid">
        <a class="navbar-brand" href="javascript:void(0)">Logo</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
          <ul class="navbar-nav me-auto">
            <li class="nav-item">
              <a class="nav-link" href="/main">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/signup">Signup</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/admin/users">Users</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/login">Login</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/logout">Logout</a>
            </li>
          </ul>
          <form class="d-flex">
            <input class="form-control me-2" type="text" placeholder="Search">
            <button class="btn btn-primary" type="button">Search</button>
          </form>
        </div>
      </div>
    </nav>
    <!-- ------------------------------------------------------------>
    <div class = "container" style = "min-height:65vh">
        contents
    </div>
    <!-- ------------------------------------------------------------>
    <div class = "bg-dark text-white text-center p-4 mt-5">
        <p>Footer</p>
    </div>
</body>
</html>