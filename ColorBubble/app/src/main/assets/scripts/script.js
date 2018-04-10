$(document).ready(function () {
    var elem, core, frame, screen, oBubble, sizeBubble, stack, touch, countBubble, stats, canSwipe, info, level;

    stack = [[], [], [], [], []];

    elem = {
        game: $('#game'),
        control: $('#control'),
        left: $('.left'),
        right: $('.right'),
        score: $('.score'),
        time: $('.time'),
        pause: $('.pause')
    }

    screen = {
        width: $(window).width(),
        height: $(window).height()
    }

    sizeBubble = screen.width / 5;
    countBubble = screen.height / sizeBubble;

    $(window)[0].addEventListener('touchstart', function (e) {
        touch.x = e.touches[0].clientX;
        touch.y = e.touches[0].clientY;
        touch.time = Date.now();
    })

    $(window)[0].addEventListener('touchend', function (e) {
        var x = e.changedTouches[0].clientX;
        var y = e.changedTouches[0].clientY;
        var time = Date.now();
        if (Math.abs(touch.y - y) < 50 && Math.abs(touch.x - x) > 75 && time - touch.time < 500) {
            if (touch.x > x) {
                swipe(false);
            } else {
                swipe(true);
            }
        }
    })

    elem.left.click(function (e) {
        swipe(false);
    })

    elem.right.click(function (e) {
        swipe(true);
    })

    elem.pause.click(function (e) {
        stats = !stats;
        if (stats) {
            loop();
        }
    })

    function init() {
        frame = 0;
        oBubble = [];
        stats = true;
        canSwipe = true;
        touch = {
            x: null,
            y: null,
            time: null
        }
        info = {
            score: 0,
            time: 0
        }
        level = {
            speed: 10
        }
        loop();
    }

    function loop() {
        core = setTimeout(function () {
            if (frame === 20) {
                info.time++;
                elem.time.text(info.time);
                if (info.time % 30 === 0) {
                    level.speed++;
                }
                frame = 0;
            }
            if (oBubble.length === 0) {
                initBubble();
            }
            moveBubble();
            fixBubble();
            checkOver();
            checkLine();
            frame++;
            if (stats) {
                loop();
            }
        }, 50);
    }

    function pInt(obj) {
        return {
            left: parseInt(obj.css('left').split('px')[0]),
            top: parseInt(obj.css('top').split('px')[0])
        }
    }

    function initBubble() {
        canSwipe = true;
        var type = Math.floor(Math.random() * 4);
        type = (type === 4) ? 3 : type;
        var column = Math.floor(Math.random() * 5);
        column = (column === 5) ? 4 : column;
        var x = screen.width / 5 * column;
        var y = screen.height;
        elem.game.append('<div class="bubble' + type + '" style="left:' + x + 'px; top:' + y + 'px"></div>');
        oBubble.push({
            elem: $('#game>div:last-child'),
            type: type,
            column: column
        })
    }

    function moveBubble() {
        for (var i = 0; i < oBubble.length; i++) {
            var top = pInt(oBubble[i].elem).top;
            var left = pInt(oBubble[i].elem).left;
            top -= level.speed;
            oBubble[i].elem.attr('style', 'left:' + left + 'px; top:' + top + 'px');
        }
    }

    function fixBubble() {
        for (var i = 0; i < oBubble.length; i++) {
            var top = pInt(oBubble[i].elem).top;
            var dataCol = stack[oBubble[i].column];
            var dataCol_l = dataCol.length;
            if (top <= sizeBubble * dataCol_l) {
                if (dataCol_l > 0 && oBubble[i].type === dataCol[dataCol_l - 1].type && oBubble[i].type > 0) {
                    canSwipe = false;
                    oBubble[i].elem.removeClass();
                    oBubble[i].type--;
                    oBubble[i].elem.addClass('bubble' + oBubble[i].type);
                    dataCol[dataCol_l - 1].elem.remove();
                    dataCol.pop();
                    info.score++;
                    elem.score.text(info.score);
                } else {
                    dataCol.push(oBubble[i]);
                    oBubble.splice(i, 1);
                    i--;
                }
            }
        }
    }

    function swipe(direc) {
        if (oBubble.length > 0 && canSwipe) {
            var lastBubble = oBubble[oBubble.length - 1];
            var top = pInt(lastBubble.elem).top;
            var left = pInt(lastBubble.elem).left;
            if (direc) {
                if (lastBubble.column < 4 && stack[lastBubble.column + 1].length * sizeBubble < top) {
                    lastBubble.column++;
                    left += sizeBubble;
                    lastBubble.elem.attr('style', 'left:' + left + 'px; top:' + top + 'px');
                }
            } else {
                if (lastBubble.column > 0 && stack[lastBubble.column - 1].length * sizeBubble < top) {
                    lastBubble.column--;
                    left -= sizeBubble;
                    lastBubble.elem.attr('style', 'left:' + left + 'px; top:' + top + 'px');
                }
            }
        }
    }

    function checkOver() {
        for (var i = 0; i < 5; i++) {
            if (stack[i].length > countBubble) {
                clearTimeout(core);
                stats = false;
                Android.gameOver(info.score, info.time);
            }
        }
    }

    function checkLine() {
        var min = Math.min(stack[0].length, stack[1].length, stack[2].length, stack[3].length, stack[4].length);
        for (var i = 0; i < min; i++) {
            var c1 = stack[0][i].type;
            var c2 = stack[1][i].type;
            var c3 = stack[2][i].type;
            var c4 = stack[3][i].type;
            var c5 = stack[4][i].type;
            if (c1 === c2 && c2 === c3 && c3 === c4 && c4 === c5) {
                for (var j = 0; j < 5; j++) {
                    stack[j][i].elem.remove();
                    stack[j].splice(i, 1);
                    for (var k = i; k < stack[j].length; k++) {
                        var top = pInt(stack[j][k].elem).top;
                        var left = pInt(stack[j][k].elem).left;
                        top -= sizeBubble;
                        stack[j][k].elem.attr('style', 'left:' + left + 'px; top:' + top + 'px');
                    }
                }
                info.score += 10;
                elem.score.text(info.score);
            }
        }
    }

    init();
})