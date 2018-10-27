$(document).ready(function () {
    var coordinates = [];
    $("#can").click(function (event) {
        var posX = event.pageX - $(this).offset().left;
        var posY = event.pageY - $(this).offset().top;

        /*
        push coordinates to input box
         */
        if ($("#coord1").val() == ""){
            $("#coord1").val(posX + "," + posY);
        }else if ($("#coord2").val() == ""){
            $("#coord2").val(posX + "," + posY);
        }else if ($("#coord3").val() == ""){
            $("#coord3").val(posX + "," + posY);
        } else if ($("#coord4").val() == "") {
            $("#coord4").val(posX + "," + posY);
        }else {
            alert("4 points have been clicked already. Please click save or clear.");
        }
    });

    $("#clr").click(function (event) {
        $("#coord1").val("");
        $("#coord2").val("");
        $("#coord3").val("");
        $("#coord4").val("");
    });
});