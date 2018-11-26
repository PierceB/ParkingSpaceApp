var context = null;
var w, h, img;
var lineWidth = 2;
var color = 'red';
var imagePath = '';

$(document).ready(function () {
    var urlParams = new URLSearchParams(window.location.search);
    var current = urlParams.get('current');
    $.ajax({
        url: 'http://dione.ms.wits.ac.za/php/getimagepath.php',
        type: 'GET',
        dataType: 'html',
        data:{
            'parkinglot': current
        },
        success:function (data) {
            if (data == 'No Images'){
                alert('No images found for this parking lot');
            } else {
                imagePath = '../../php/WitsImages/' + data;
                init(imagePath);
            }

        }
    });

    $("#parkingspace").change(function (event) {
        clear();
        var coordinates = null;
        var parkingbay = $('#parkingspace option:selected').val();
        $.ajax({
            url: 'http://dione.ms.wits.ac.za/php/getcoords.php',
            type: 'GET',
            dataType: 'html',
            data:{
                'parkingbay': parkingbay
            },
            success: function (data) {
                coordinates = JSON.parse(data);
                var coord1 = coordinates.coord1.split(',');
                var coord2 = coordinates.coord2.split(',');
                var coord3 = coordinates.coord3.split(',');
                var coord4 = coordinates.coord4.split(',');

                drawLine(coord1, coord2);
                drawLine(coord2, coord3);
                drawLine(coord3, coord4);
                drawLine(coord4, coord1);
            }
        });

    });
    $("#can").click(function (event) {
        var posX = event.pageX - $(this).offset().left;
        var posY = event.pageY - $(this).offset().top;
        /*
        push coordinates to input box
         */
        if ($("#coord1").val() == ""){
            $("#coord1").val(posX + "," + posY);
            drawDot(posX, posY);
        }else if ($("#coord2").val() == ""){
            $("#coord2").val(posX + "," + posY);
            drawDot(posX, posY);
        }else if ($("#coord3").val() == ""){
            $("#coord3").val(posX + "," + posY);
            drawDot(posX, posY);
        } else if ($("#coord4").val() == "") {
            $("#coord4").val(posX + "," + posY);
            drawDot(posX, posY);
        }else {
            alert("4 points have been clicked already. Please click save or clear.");
        }
    });

    $("#can1").click(function (event) {
        var posX = event.pageX - $(this).offset().left;
        var posY = event.pageY - $(this).offset().top;
        /*
        push coordinates to input box
         */
        if ($("#coord11").val() == ""){
            $("#coord11").val(posX + "," + posY);
            drawDot1(posX, posY);
        }else if ($("#coord21").val() == ""){
            $("#coord21").val(posX + "," + posY);
            drawDot1(posX, posY);
        }else if ($("#coord31").val() == ""){
            $("#coord31").val(posX + "," + posY);
            drawDot1(posX, posY);
        } else if ($("#coord41").val() == "") {
            $("#coord41").val(posX + "," + posY);
            drawDot1(posX, posY);
        }else {
            alert("4 points have been clicked already. Please click save or clear.");
        }
    });

    $("#clr").click(function (event) {
        $("#coord1").val("");
        $("#coord2").val("");
        $("#coord3").val("");
        $("#coord4").val("");
        erase();
    });
});

function init(image) {
    context = $('#can')[0].getContext('2d');
    img = new Image();
    img.onload = function () {
        context.drawImage(img,0,0, $('#can')[0].width, $('#can')[0].height);
    };
    img.src = image;
    w = $('#can')[0].width;
    h = $('#can')[0].height;
    imagePath = image;
}

function erase() {
    var m = confirm("Want to clear");
    if (m) {
        context.clearRect(0, 0, w, h);
        img = new Image();
        img.onload = function() {
            context.drawImage(img,0,0, w, h);
        };
        img.src = imagePath;
    }
}

function clear() {
    context.clearRect(0, 0, w, h);
    img = new Image();
    img.onload = function() {
        context.drawImage(img,0,0, w, h);
    };
    img.src = imagePath;
}

function drawDot(x, y) {
    context = $('#can')[0].getContext('2d');
    context.fillStyle = color;
    context.beginPath();
    context.arc(x, y, 2, 0, 2*Math.PI);
    context.fill();
    context.closePath();
}

function drawDot1(x, y) {
    context = $('#can1')[0].getContext('2d');
    context.fillStyle = color;
    context.beginPath();
    context.arc(x, y, 2, 0, 2*Math.PI);
    context.fill();
    context.closePath();
}

function drawLine(point1, point2) {
    context = $('#can')[0].getContext('2d');
    context.beginPath();
    context.moveTo(point1[0], point1[1]);
    context.lineTo(point2[0], point2[1]);
    context.strokeStyle = color;
    context.lineWidth = lineWidth;
    context.stroke();
    context.closePath();

}
