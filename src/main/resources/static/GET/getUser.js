$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/users/5731f2f8c2ae26048a1a46a4"
    }).then(function(data) {
        $('#username .val').append(data.username);
        $('#email .val').append(data.email);
    });
});
