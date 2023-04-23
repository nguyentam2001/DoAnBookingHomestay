

//Flot Pie Chart
$(function() {

    $.ajax({
        url:`${DashboardURL.ROOT}`,
        type: "GET",
         dataType: "json",
          async: false,
          success: function(data) {
             var plotObj = $.plot($("#flot-pie-chart"), data, {
                    series: {
                        pie: {
                            show: true
                        }
                    },
                    grid: {
                        hoverable: true
                    },
                    tooltip: true,
                    tooltipOpts: {
                        content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
                        shifts: {
                            x: 20,
                            y: 0
                        },
                        defaultTheme: false
                    }
                });
          },
          error: function(error){
            console.log(error)
          }
    })


});

