var w, h;
var lineWidth = 2;
var color = 'red';
var imagePath = '';
var imagePathTop = '';

$(document).ready(function () {
    var urlParams = new URLSearchParams(window.location.search);
    var current = urlParams.get('current');

    $.ajax({
        url: 'http://dione.ms.wits.ac.za/php/getimagepath.php',
        type: 'GET',
        dataType: 'html',
        data:{
            'parkinglot': current,
            'action': 'classify'
        },
        success:function (data) {
            if (data == 'No Images'){
                alert('No images found for this parking lot');
            } else {
                var context = $('#can')[0].getContext('2d');
                var path = '../../php/WitsImages/' + data;
                imagePath = path;
                init(path, context);
            }

        }
    });

    $.ajax({
        url: 'http://dione.ms.wits.ac.za/php/getimagepath.php',
        type: 'GET',
        dataType: 'html',
        data:{
            'parkinglot': current,
            'action': 'calibrate'
        },
        success: function (data) {
            if (data == 'No Images'){
                alert('No images found for this parking lot');
            } else {
                var context = $('#cantop')[0].getContext('2d');
                var path = '../../php/WitsImages/' + data;
                imagePathTop = path;
                init(path, context);
            }
        }
    });

    $('#parkingspace').change(function (event) {
        var context = $('#can')[0].getContext('2d');
        clear(imagePath, context);
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
                var context = $('#can')[0].getContext('2d');
                drawLine(coord1, coord2, context);
                drawLine(coord2, coord3, context);
                drawLine(coord3, coord4, context);
                drawLine(coord4, coord1, context);
            }
        });
    });
    
    $('#cantop').click(function (event) {
        var context = $('#cantop')[0].getContext('2d');
        var posX = event.pageX - $(this).offset().left;
        var posY = event.pageY - $(this).offset().top;
        /*
        push coordinates to input box
         */
        if ($("#coord1").val() == ""){
            $("#coord1").val(posX + "," + posY);
            drawDot(posX, posY, context);
        }else if ($("#coord2").val() == ""){
            $("#coord2").val(posX + "," + posY);
            drawDot(posX, posY, context);
        }else if ($("#coord3").val() == ""){
            $("#coord3").val(posX + "," + posY);
            drawDot(posX, posY, context);
        } else if ($("#coord4").val() == "") {
            $("#coord4").val(posX + "," + posY);
            drawDot(posX, posY, context);
        }else {
            alert("4 points have been clicked already. Please click save or clear.");
        }
    });

    $("#clr").click(function (event) {
        var context = $('#cantop')[0].getContext('2d');
        $("#coord1").val("");
        $("#coord2").val("");
        $("#coord3").val("");
        $("#coord4").val("");
        erase(imagePathTop, context);
    });
});

function init(image, context) {
    var img = new Image();
    img.onload = function () {
        context.drawImage(img,0,0, $('#can')[0].width, $('#can')[0].height);
    };
    img.src = image;
    w = $('#can')[0].width;
    h = $('#can')[0].height;
}

function erase(image, context) {
    var ctx = context;
    var m = confirm("Want to clear");
    if (m) {
        ctx.clearRect(0, 0, w, h);
        var img = new Image();
        img.onload = function() {
            ctx.drawImage(img,0,0, w, h);
        };
        img.src = image;
    }
}

function clear(image, context) {
    var ctx = context;
    ctx.clearRect(0, 0, w, h);
    var img = new Image();
    img.onload = function() {
        ctx.drawImage(img,0,0, w, h);
    };
    img.src = image;
}

function drawDot(x, y, context) {
    var ctx = context;
    ctx.fillStyle = 2;
    ctx.beginPath();
    ctx.arc(x, y, 2, 0, 2*Math.PI);
    ctx.fill();
    ctx.closePath();
}

function drawLine(point1, point2, context) {
    var ctx = context;
    ctx.beginPath();
    ctx.moveTo(point1[0], point1[1]);
    ctx.lineTo(point2[0], point2[1]);
    ctx.strokeStyle = 'red';
    ctx.lineWidth = 2;
    ctx.stroke();
    ctx.closePath();

}