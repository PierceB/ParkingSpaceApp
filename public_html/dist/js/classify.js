var context = null;
var w, h, img;
var lineWidth = 2;
var color = 'red';

$(document).ready(function () {
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
});

function init() {
    context = $('#can')[0].getContext('2d');
    img = new Image();
    img.onload = function () {
        context.drawImage(img,0,0, $('#can')[0].width, $('#can')[0].height);
    };
    img.src = '../../images/wits_msl/02.png';
    w = $('#can')[0].width;
    h = $('#can')[0].height;
}

function clear() {
    context.clearRect(0, 0, w, h);
    img = new Image();
    img.onload = function() {
        context.drawImage(img,0,0, w, h);
    };
    img.src = '../../images/wits_msl/02.png';
}

function drawLine(point1, point2) {
    context = $('#can')[0].getContext('2d');
    context.beginPath();
    context.moveTo(point1[0], point1[1]);
    context.lineTo(point2[0], point2[1]);
    context.fillStyle = 'red';
    context.lineWidth = lineWidth;
    context.stroke();
    context.closePath();

}