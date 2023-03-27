 $(document).ready(function() {
    render();
    renderModal();
    renderTableSmall();
 });

function renderModal(){
$('#homestayFormModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var recipient = button.data('whatever') // Extract info from data-* attributes
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var modal = $(this)
  modal.find('.modal-title').text('Nhập hông tin homstay')
  modal.find('.modal-body input').val(recipient)
    })

}
function renderTableSmall(){
     var html = `
      <tr>
               <td>
                     <select class="form-control" id="product-name">
                                           <option value="1">Đơn</option>
                                               <option value="2">Đôi</option>
                                                    </select>
                                                     </td>
                                                    <td>
                                                    <input type="text" name="address" class="form-control"  placeholder="diện tích">
                                               </td>
                                              <td>
                                            <input type="text" name="address" class="form-control"  placeholder="mô tả">
                                     </td>
                                 <td><button class="btn btn-danger remove"><i class="fa fa-times" aria-hidden="true"></i></button></td>
                       </tr>

     `;

    	$("#addProduct").click(function(){
    		$('#roomTable .roomBody').append(html);
    });
    $(document).on('click','.remove',function(){
        $(this).parents('tr').remove();
    });
}

function render() {
 $('#dataTables-example').DataTable({
       ajax:{
            url:HomestayURL.ROOT,
            dataSrc: '',
       },

       columns: [
                   { data: 'homestayId' },
                   { data: 'homestayName' },
                   {
                        data: 'homestayType'

                    },
                   { data: 'price' },
                   { data: 'status' },
                   { data: 'address.addressName' },
                   { data: null }
      ],

      createdRow: function( row, data, dataIndex){
              if( data['status'] == false){
                 $(row).addClass('row-color')
                }
       },

      columnDefs:[
              {
               render: function (data, type, row) {
                    if(data==2){
                        return 'Thương gia'
                    }else{
                     return 'Thường' ;
                    }
                },
               targets: 2,
              },
              {
              render: function (data, type, row) {
                                  if(data==false){
                                    return 'Trống'
                                  }else{
                                   return 'Đã thuê' ;
                                  }
                              },

              targets: 4,
              },

               {
                    render: function (data, type, row) {
                                    var html = '';
                                         html+= `<span class="link-color" id="'${row.homestayId}'" onclick="showHomestayDetail('${row.homestayId}');"></i>View |</span>`
                                        if(row['status']==true){
                                            html+= ` <span class="link-color" id="'${row.homestayId}'" onclick="update('${row.homestayId}');"></i>Update</span>`
                                        }else{
                                            html+= ` <span class="link-color" data-toggle="modal" data-target="#deleteModal${row.homestayId}" onclick="delete('${row.homestayId}');"></i>Delete</span>
                                            <div class="modal fade" id="deleteModal${row.homestayId}" tabindex="-1" role="dialog"
                                                          aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                          <div class="modal-dialog" role="document">
                                                              <div class="modal-content">
                                                                  <div class="modal-header">
                                                                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                          <span aria-hidden="true">&times;</span>
                                                                      </button>
                                                                  </div>
                                                                  <div class="modal-body">
                                                                      Bạn có muốn xoá homestay ${row.homestayName} ?
                                                                  </div>
                                                                  <div class="modal-footer">
                                                                      <button type="button" class="btn btn-primary" onclick="del('${
                                                                        row.homestayId
                                                                      }');">Ok</button>
                                                                      <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                  </div>
                                                              </div>
                                                          </div>
                                                 </div>
                                            `
                                        }
                                        return html;
                                      },
                   targets: 6,
             },


      ],
       responsive: true
    });

}

function del(id){
    $.ajax({
        url: `${HomestayURL.ROOT}${id}`,
        type: App.DELETE,
        async: false,
        success: function () {
          toastr.success(App.DELETE_SUCCESS_MSG);
             hideModal(`#deleteModal${id}`)
          destroyTable();
          render();
        },
        error: function (e) {
        console.log(e);
          if(e.responseJSON.status==500){
            toastr.error("Homestay này đã phát sinh hoá đơn, không thể xoá!")
          }
           hideModal(`#deleteModal${id}`)
          destroyTable();
          render();
        },
      });
}

function destroyTable(){
     $('#dataTables-example').DataTable().destroy();
}
function save(){
      $.ajax({
        url: `${HomestayURL.ROOT}`,
        type: App.POST,
        async: false,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
          homestayName: $("[name='homestayName']").val(),
          status: $("[name='status']").val(),
          address:{
             addressId: $("[name='addressId']").val(),
          },
          homestayType: $("[name='homestayType']").val(),
          price: $("[name='price']").val(),
          description: $("[name='description']").val(),
        }),
        success: function (res) {

          toastr.success(App.SUCCESS_MSG);
         hideModal("#homestayFormModal")
          destroyTable();
          render();
        },
        error: function (e) {
          toastr.error(e.responseJSON.message);
         hideModal("'#homestayFormModal'")
          destroyTable();
          render();
        },
      });
}

function hideModal(modalId){
$(modalId).modal('hide');
$('body').removeClass('modal-open');
$('.modal-backdrop').remove();
}

function update(id){
}
function showHomestayDetail(id){
}