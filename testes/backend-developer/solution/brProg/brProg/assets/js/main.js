var calculaBot = $("footer").last().offset().top + $("footer").height();

$(window).height() > calculaBot && (calculaFooter = $(window).height() - calculaBot, 
$('<div style="min-height:' + calculaFooter + 'px;"></div>').insertBefore("footer")), 
$(window).resize(function() {
    var calculaBot = $("footer").last().offset().top + $("footer").height();
    $(window).height() > calculaBot && (calculaFooter = $(window).height() - calculaBot, 
    $('<div style="min-height:' + calculaFooter + 'px;"></div>').insertBefore("footer"));
});

var forEach = function(t, o, r) {
    if ("[object Object]" === Object.prototype.toString.call(t)) for (var c in t) Object.prototype.hasOwnProperty.call(t, c) && o.call(r, t[c], c, t); else for (var e = 0, l = t.length; l > e; e++) o.call(r, t[e], e, t);
}, hamburgers = document.querySelectorAll(".hamburger");

hamburgers.length > 0 && forEach(hamburgers, function(hamburger) {
    hamburger.addEventListener("click", function() {
        this.classList.toggle("is-active");
    }, !1);
}), $(document).ready(function() {
    $("#loginIndex").click(function() {
        $("#ModalLogin").modal();
    });
});


