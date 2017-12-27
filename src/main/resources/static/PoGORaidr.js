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
    alert('Messenger Extensions JS SDK is done loading')
};