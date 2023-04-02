$(document).ready(function () {
  render();
});
function render() {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  $("#dataTables-example").DataTable({
    ajax: {
      url: `HomestayURL.ROOT${urlParams}`,
      dataSrc: "rooms",
    },

    columns: [
      { data: "roomId" },
      { data: "roomName" },
      { data: "bedNumbers" },
      { data: "roomDescription" },
      { data: "area" },
      { data: "roomType" },
      { data: "status" },
      { data: "numberOfPerson" }
    ],
    columnDefs: [
    {
         render: function (data, type, row) {
           let roomsBlank=0;
           for(let i = 0; i < row["rooms"].length; i++) {
               if(row["rooms"][i]["status"]==false){
                    roomsBlank++
               }
           }
          let html=`Tổng ${row["rooms"].length} (Trống ${roomsBlank})`
           return html
         },
         targets: 4,
       },
    ],
    responsive: true,
  })
}

