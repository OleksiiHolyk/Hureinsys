$(document).ready(function () {
    var menuId = $("ul.nav").first().attr("id");

    $.ajax({
        method: "DELETE",
        url: "http://localhost:8080/users/573317c4c2ae6411a866dd2c"
    });
});
