/**
 * 提交问题回复
 */
function postComment() {
    var questionId = $("#question_id").val();
    let content = $("#comment_content").val();
    comment_action(questionId, 1, content);
}

/**
 * 回复评论
 * @param e
 */
function answer_comment(e) {
    let commentId = e.getAttribute("data-id");
    let content = $("#input-" + commentId).val();
    comment_action(commentId, 2, content);
}

/**
 * 执行操作
 * @param targetId
 * @param type
 * @param content
 */
function comment_action(targetId, type, content) {
    if (!content) {
        alert("评论内容不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code === 200) {
                window.location.reload();
            } else {
                if (response.code === 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=657a78a4f9c60b65dde2&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    let id = e.getAttribute("data-id");
    let subCommentContainer = $("#comment-" + id);
    let hasClass = subCommentContainer.hasClass("in");
    if (hasClass) {
        subCommentContainer.removeClass("in");
        e.classList.remove("active");
    } else {
        if (subCommentContainer.children().length !== 1) {
            //展开评论
            subCommentContainer.addClass("in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {

                    var mediaLeft=$("<div/>",{
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        src: comment.user.avatarUrl
                    }));

                    var mediaBody = ($("<div/>", {
                        "class": "media-body"
                    })).append($("<h5/>", {
                        "class": "media-heading",
                        "text": comment.user.name
                    })).append($("<div/>", {
                        "text": comment.content
                    })).append($("<div/>", {
                        "class": "menu pull-right",
                        "text": moment(comment.gmtCreate).format("YYYY-MM-DD")
                    }));

                    var c = $("<div/>", {
                        "class": "media col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                    }).append(mediaLeft).append(mediaBody);
                    subCommentContainer.prepend(c);
                });

            });
            subCommentContainer.addClass("in");
            e.classList.add("active");
        }
    }
}

/**
 * 从标签中选择到框内
 * @param value
 */
function selectTag(e) {
    let value = e.getAttribute("data-tag");
    let previous = $("#tag").val();
    if(previous){
        let strings = previous.split(',');
        for (let string of strings) {
            if(string===value)
                return;
        }
        $("#tag").val(previous+','+value);
    }else{
        $("#tag").val(value);
    }
}

/**
 * 点击弹出标签选择框
 */
function displayTag() {
    $("#tag-table").show();
}