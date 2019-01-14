//写cookies，一个小时过期
function setCookie(board) {
    var exp = new Date();
    exp.setTime(exp.getTime() + 60 * 60 * 1000);
    document.cookie = "board=" + escape(board) + ";expires=" + exp.toGMTString() + ";path=/";
}

//读取cookies
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

function initBoard() {
    var board = new Array();
    for (var i = 0; i < 16; i++) {
        board[i] = new Array();
        for (var j = 0; j < 16; j++) {
            board[i][j] = 0;
        }
    }
    return board
}





var board = initBoard()
setCookie(JSON.stringify(board).toString())

