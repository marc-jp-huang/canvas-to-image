$('.upload-btn').on('click', function (){
    $('#fileToUpload').click();
    $('.progress-bar').text('0%');
    $('.progress-bar').width('0%');
});

$("#fileToUpload").on("change",function() {
    var file = $(this)[0].files[0];
    var formData = new FormData();
    formData.append('fileToUpload', file, file.name);
    formData.append('_filename',file.name);
    uploadFile(formData);
});

function uploadFile(formData){
    $.ajax({
        url: 'upload.jsp',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(data){
            setImageToCanvas("/uploads/"+data.replace(/\r?\n|\r/g,""));
        },
        xhr: function() {
            // create an XMLHttpRequest
            var xhr = new XMLHttpRequest();
            // listen to the 'progress' event https://xhr.spec.whatwg.org/#interface-progressevent
            xhr.upload.addEventListener('progress', function(event) {

                if (event.lengthComputable) {
                    // calculate the percentage of upload completed
                    var percentComplete = event.loaded / event.total;
                    percentComplete = parseInt(percentComplete * 100);

                    // update the Bootstrap progress bar with the new percentage
                    $('.progress-bar').text(percentComplete + '%');
                    $('.progress-bar').width(percentComplete + '%');

                    // once the upload reaches 100%, set the progress bar text to done
                    if (percentComplete === 100) {
                        $('.progress-bar').html('Done');
                    }
                }

            }, false);
            return xhr;
        }
    });//end of ajax
}

function setImageToCanvas(filename){
    var c = document.getElementById("myCanvas");
    var ctx = c.getContext("2d");
    var img = new Image();
    img.onload = function() {
        ctx.drawImage(img, 0, 0, img.width, img.height,0,25, c.width,100);
    };
    img.src = filename;
}

$("#caption1").on("keyup",function(){
    drawText($(this).val(),"head");
});
$("#caption2").on("keyup",function(){
    drawText($(this).val(),"bottom");
});

function drawText(text,where){
    cleanArea(where);
    var canvas = document.getElementById("myCanvas");
    var textX = canvas.width/2;
    var textY = 12;
    if(where=="bottom"){
        textY = canvas.height-2;
    }
    var ctx = canvas.getContext("2d");
    ctx.font = "14px Arial";
    ctx.fillStyle = "white";
    ctx.textAlign = "center";
    ctx.fillText(text, textX, textY);
}
function cleanArea(where){
    var canvas = document.getElementById("myCanvas");
    var ctx = canvas.getContext("2d");
    ctx.fillStyle = "black";
    var areaY = 0;
    if(where=="bottom"){
        areaY = canvas.height-15;
    }
    ctx.fillRect(0,areaY,canvas.width,15);
}