(function(d, s, id){
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.com/en_US/messenger.Extensions.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'Messenger'));

var isMessengerLoaded = false;

window.extAsyncInit = function () {
    isMessengerLoaded = true;
};

function testRaidCreation() {
    if(isMessengerLoaded){
        var data = {
            "host":"Test Trainer",
            "gymId":"12345",
            "date":"2017.12.24",
            "time":"12:00"
        };

        $.ajax({
            url: "https://pogo-raidr.herokuapp.com/raid",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, status, jqXHR) {
                var element = document.createElement("h2");
                element.text = data.host;
                element.appendTo('body');
            },
            error: function (jqXHR, status) {
                // error handler
                console.log(jqXHR);
                alert('fail' + status.code);
            }
        })
    }
}