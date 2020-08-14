<%-- 
    Document   : index
    Created on : Jul 7, 2020, 5:01:29 PM
    Author     : nhatp
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header">
                                        <h3 class="text-center font-weight-light my-4">Login</h3>
                                        <p class="alert-danger">
                                            <s:property value="#request.error"/>
                                        </p>
                                    </div>
                                    <div class="card-body">
                                        <form action="LoginAction" method="POST">
                                            <div class="form-group"><label class="small mb-1" >Username</label><input class="form-control py-4" type="text" name="username" placeholder="Enter username" /></div>
                                            <div class="form-group"><label class="small mb-1">Password</label><input class="form-control py-4" id="inputPassword" type="password" name="password" placeholder="Enter password" /></div>
                                            <div class="g-recaptcha" id="reCAPTCHA" data-sitekey="6Lc_6KwZAAAAAMyqDFUrb85cLBk0TxoLsl37MI8g"></div>
                                            <input type="hidden" id="reCAPTCHAResult" name="reCAPTCHAResult"/>
                                            <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0"><input class="btn btn-primary" type="submit" value="Login" onclick="checkReCAPTCHA()"></div></br>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; EXTRA</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script type="text/javascript">
            function checkReCAPTCHA() {
                var response = grecaptcha.getResponse();
                if (response.length == 0) {
                    document.getElementById("reCAPTCHAResult").value = 'False';
                } else {
                    document.getElementById("reCAPTCHAResult").value = 'True';
                }
            }
            ;
        </script>
    </body>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
            async defer>
    </script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
</html>
