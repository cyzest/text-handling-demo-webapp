$(function() {

    $('#search').click(function() {

        $("#textGroup").text("");
        $("#otherGroup").text("");

        var url = $.trim($("#url").val());
        var textType = $.trim($("#textType").val());
        var textGroupUnit = $.trim($("#textGroupUnit").val());

        $("#loading").text("Loading...");

        $.ajax({
            url: "/api/v1/text?url=" + encodeURI(url).replace(/#/g, '%23') + "&textType=" + textType + "&textGroupUnit=" + textGroupUnit,
            type: "GET",
            dataType: "json",
            success: function (data) {

                $("#loading").text("");

                var groupTextList = data.groupTextList;
                var otherText = data.otherText;

                var group = "";

                if (groupTextList !== null && groupTextList.length > 0) {
                    groupTextList.forEach(function (groupText) {
                        group += groupText;
                    });
                    $("#textGroup").text(group);
                }

                if (otherText != null) {
                    $("#otherGroup").text(otherText);
                }
            },
            error: function (error) {

                $("#loading").text("");

                if (error.status === 400) {
                    alert("파라미터가 유효하지 않습니다.");
                } else {
                    alert(error.statusText);
                }
            }
        });

    });

});