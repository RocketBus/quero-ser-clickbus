$(document).ready(function() {
    "Modal" == $.cookie("modalCookie") ? $("#ModalLogin").modal() : $.removeCookie("modalCookie");
});

$('#Quantidade').on("input", function (e) {
    $(this).val($(this).val().replace(/,/g, ""));
});


