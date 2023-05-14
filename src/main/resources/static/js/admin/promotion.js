$(document).ready(function () {
  render();
});
function render() {
  $("#promotionTable").DataTable({
    ajax: {
      url: `${PromotionURL.ROOT}`,
      dataSrc: "",
    },
    columns: [
      { data: "promotionId" },
      { data: "promotionName" },
      { data: "percentDiscount" },
      { data: "homestay.homestayName" },
      { data: "startDate" },
      { data: "endDate" },
      { data: "description" },
    ],
    columnDefs: [
       {
                   targets: [4,5],
                   render: $.fn.dataTable.render.moment('YYYY-MM-DD','DD/MM/YYYY' )
               },
       {
            targets:2,
            render: function(data, type,row) {
                return `${data}%`
            }
       },
             { targets: [ 2 ], className: 'dt-right' },

      {
        render: function (data, type, row) {
          var html = "";
          html += `<span class="link-color" id="'${row.promotionId}'" onclick="showPromotionDetail('${row.promotionId}');"></i>Xem |</span>`;
          html += ` <span class="link-color" data-toggle="modal" data-target="#deleteModal${row.promotionId}" onclick="delete('${row.promotionId}');"></i> Xoá</span>
                                          <div class="modal fade" id="deleteModal${row.promotionId}" tabindex="-1" role="dialog"
                                                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    Bạn có muốn xoá promotion ${row.promotionName} ?
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-primary" onclick="del('${row.promotionId}');">Ok</button>
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                </div>
                                                    </div>
                                    </div>
                  </div>
             `;
          return html;
        },
        targets: 7,
      },
    ],
    responsive: true,
  });
}

function del(id) {
  $.ajax({
    url: `${PromotionURL.ROOT}${id}`,
    type: App.DELETE,
    async: false,
    success: function () {
      toastr.success(App.DELETE_SUCCESS_MSG);
      hideModal(`#deleteModal${id}`);
      destroyTable();
      render();
    },
    error: function (e) {
      console.log(e);
      if (e.responseJSON.status == 500) {
        toastr.error("Mã giảm giá này đã phát sinh hoá đơn, không thể xoá!");
      }
      hideModal(`#deleteModal${id}`);
      destroyTable();
      render();
    },
  });
}

function destroyTable() {
  $("#promotionTable").DataTable().destroy();
}



function save() {
    $.ajax({
        url: `${PromotionURL.ROOT}`,
        type: App.POST,
        async: false,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
          promotionId: $("[name='promotionId']").val(),
          promotionName: $("[name='promotionName']").val(),
          percentDiscount: $("[name='percentDiscount']").val(),
          startDate: $("[name='startDate']").val(),
          endDate: $("[name='endDate']").val(),
          description: $('#description').val(),
          promotionName: $("[name='promotionName']").val(),
          homestay: {
                homestayId: $("[name='homestayId']").val(),
          }
        }),
        success: function (res) {
          location.reload();
          destroyTable();
          render();
        },
        error: function (e) {
          location.reload();
          destroyTable();
          render();
        },
      });
}

function hideModal(modalId) {
console.log(modalId)
  $(modalId).modal("hide");
  $(modalId).hide();
  $("body").removeClass("modal-open");
  $(".modal-backdrop").remove();
}

