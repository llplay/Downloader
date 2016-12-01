
$(document).ready(function() {

	$('.popup').css({ opacity: 0 });

	$('.form-container').submit(function() {
		if(!$('.form-container .search-field').val() || $('.form-container .search-field').val() == "Type search text here...") {
			$('.popup').css({ opacity: 0 });
			$('.popup').animate(
				{ opacity: 1 },
				{
					duration: 'slow',
					easing: 'easeOutBounce'
				});
			return false;
		}
	});

	$('.form-container .search-field').focus(function() {
		if($(this).val() == "Type search text here...") {
			this.value = "";
		}
	});

	$('.form-container .search-field').keydown(function() {
		$('.popup').css({ opacity: 0 });
	});

	// upload
	$('.upload-btn').click(function() {
        $.ajax({
            url: "/upload",
            type: "POST",
            data: new FormData($("#upload-file-form")[0]),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function () {
                // Handle upload success
                $("#upload-file-message").text("File succesfully uploaded");
            },
            error: function () {
                // Handle upload error
                $("#upload-file-message").text(
                    "File not uploaded (perhaps it's too much big)");
            }
        });
	});
});
