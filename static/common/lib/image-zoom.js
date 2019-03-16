
  function imgZoom() {
    var zoomDefaults = {
      styles: {
        zoomImage: {
          cursor          : 'zoom-out',
          position        : 'absolute',
          transition      : 'transform 300ms',
          transform       : 'translate3d(0, 0, 0) scale(1)',
          transformOrigin : 'center center',
          willChange      : 'transform, top, left'
        },
        zoomContainer: {
          position        : 'fixed',
          top             : 0,
          right           : 0,
          bottom          : 0,
          left            : 0,
          zIndex          : 1024,
        },
        overlay: {
          position        : 'absolute',
          top             : 0,
          right           : 0,
          bottom          : 0,
          left            : 0,
          backgroundColor : '#FFF',
          opacity         : 0,
          transition      : 'opacity 300ms',
        },
        btn: {
          position        : 'absolute',
          bottom          : 12,
          right           : 12,
          padding         : '4px 10px',
          border          : '1px solid #e9e9e9',
          borderRadius    : 2,
          fontSize        : 12,
          color           : '#999',
        },
        btnHover: {
          color           : '#666',
        },
      },
    };
    var zoomOriginImage = null;
    var zoomTimer = null;
    // zoom in
    $('.markdown').on('click', 'img', function() {
      zoomOriginImage = $(this).get(0);

      var $wrap = $('<div />');
      $wrap.css(zoomDefaults.styles.zoomContainer);

      var $overlay = $('<div class="overlay" />');
      $overlay.css(zoomDefaults.styles.overlay);

      var $img = $(this).clone();
      $img.css(getImageStyle(zoomOriginImage, false));

      var $btn = $('<a target="_blank" />');
      $btn.attr('href', $img.attr('src')).text('查看原图');
      $btn.css(zoomDefaults.styles.btn);

      $btn.hover(function() {
        $(this).css(zoomDefaults.styles.btnHover);
      }, function() {
        $(this).css(zoomDefaults.styles.btn);
      });

      $wrap.append($overlay).append($img).append($btn);
      $('#zoom-img').append($wrap);

      // transition
      $overlay.css({
        opacity: 1,
      });
      $img.css(getImageStyle($img.get(0), true));
    });

    // zoom out
    $('#zoom-img').on('click', function() {
      var $zoom = $(this);
      $zoom.find('img').css(getImageStyle(zoomOriginImage, false));
      $zoom.find('.overlay').css({
        opacity: 0
      });

      if (zoomTimer) {
        clearTimeout(zoomTimer);
      }
      zoomTimer = setTimeout(function() {
        $zoom.html('');
        zoomOriginImage = null;
      }, 150);
    });

    $(window).on('scroll', function() {
      $('#zoom-img').html('');
      zoomOriginImage = null;
    });

    $(window).on('resize', function() {
      if ($('#zoom-img img').length) {
        $('#zoom-img img').css(getImageStyle(zoomOriginImage, true));
      }
    });

    function getImageStyle(image, isZoom) {
      var imageOffset = image.getBoundingClientRect();
      var top = imageOffset.top;
      var left = imageOffset.left;
      var width = image.width;
      var height = image.height;

      var style = {
        top: top,
        left: left,
        width: width,
        height: height
      };

      if (!isZoom) {
        return Object.assign({}, zoomDefaults.styles.zoomImage, style);
      }

      // Get the the coords for center of the viewport
      var viewportX = window.innerWidth / 2;
      var viewportY = window.innerHeight / 2;

      // Get the coords for center of the original image
      var imageCenterX = imageOffset.left + image.width / 2;
      var imageCenterY = imageOffset.top + image.height / 2;

      // Get offset amounts for image coords to be centered on screen
      var translateX = viewportX - imageCenterX;
      var translateY = viewportY - imageCenterY;

      // Figure out how much to scale the image so it doesn't overflow the screen
      var scale = getScale(width, height);

      var zoomStyle = {
        transform: 'translate3d(' + translateX + 'px, ' + translateY + 'px, 0) scale(' + scale + ')',
      }

      return Object.assign({}, zoomDefaults.styles.zoomImage, style, zoomStyle);
    }

    function getScale(width, height) {
      var totalMargin = 40;
      var scaleX = window.innerWidth / (width + totalMargin);
      var scaleY = window.innerHeight / (height + totalMargin);
      return Math.min(scaleX, scaleY);
    }
  }
