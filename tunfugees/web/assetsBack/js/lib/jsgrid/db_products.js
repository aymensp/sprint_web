(function () {

    var db_products = {

        loadData: function (filter) {
            return $.grep(this.products, function (client) {
                return (!filter.Label || client.Label.indexOf(filter.Label) > -1)
                    && (filter.Price || client.price === filter.Price)
                    && (!filter.Category || client.Category === filter.Category)
                    && (!filter.Quantity || client.remaining === filter.Quantity);
            });
        },

        insertItem: function (insertingClient) {
            this.products.push(insertingClient);
        },

        updateItem: function (updatingClient) {
            console.log(updatingClient);
            $.ajax({
                type: "get",
                url: Routing.generate('products_edit', {id: updatingClient.ID}),
                data:   "updatedProduct="+JSON.stringify(updatingClient),
                contentType: "application/j-son;charset=UTF-8"
            });
        },

        deleteItem: function (deletingClient) {
            $.ajax({
                type: "get",
                url: Routing.generate('products_delete', {id: deletingClient.ID}),
                data:   "deletedProduct="+JSON.stringify(deletingClient),
                contentType: "application/j-son;charset=UTF-8",
                success:    function (data) {
                    if(data.success === true){
                        var clientIndex = $.inArray(deletingClient, db_products.products);
                        this.products.splice(clientIndex, 1);
                    }
                }
            });
        }

    };

    db_products.products = [];
    for (var i = 0; i < data_products.length; i++) {
        db_products.products.push({
            "ID": data_products[i].id,
            "Picture": data_products[i].pic1,
            "Label": data_products[i].label,
            "Category": data_products[i].category.id,
            "Price": data_products[i].price,
            "Quantity": data_products[i].remaining
        });
    }
    db_products.categories = [];
    db_products.categories.push({
        "ID": 0,
        "Label": "(All)"
    });
    for (var i = 0; i < data_categories.length; i++) {
        db_products.categories.push({
            "ID": data_categories[i].id,
            "Label": data_categories[i].label
        });
    }
    window.db_products = db_products;
}());