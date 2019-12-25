//
//  ProductList.swift
//  RecipeSelector
//
//  Created by Артем on 16/10/2019.
//  Copyright © 2019 ArtemTkachuk. All rights reserved.
//

import UIKit
import SQLite


class ProductList: UITableViewController, UISearchResultsUpdating {
    var db: [String] = Database.shared.ingredientsResult
    
    var checkMarkArray: [String]?
    var ingredientsResult: [String] = []
    
    private let searchController = UISearchController(searchResultsController: nil)
    var arr: [String] = []
    lazy var productsArray: [ProductModel] = []
    
    
    private var searchResults = [ProductModel]()
    
    private var searchBarIsEmpty: Bool {
        guard let text = searchController.searchBar.text else { return false }
        return text.isEmpty
    }
    
    private var isFiltering: Bool {
        return searchController.isActive && !searchBarIsEmpty
    }

//    private func filterProducts(for searchText: String) {
//        searchResults = productsArray.filter({ (product: String) -> Bool in
//            return product.lowercased().contains(searchText.lowercased())
//        })
//        tableView.reloadData()
//    }
    
    private func filterProducts(for searchText: String) {
        searchResults = productsArray.filter{ (product: ProductModel) -> Bool in
            return product.name.lowercased().contains(searchText.lowercased())
        }
           tableView.reloadData()
       }
    
    func updateSearchResults(for searchController: UISearchController) {
        filterProducts(for: searchController.searchBar.text ?? "")
    }

    
    override func viewDidLoad() {


        super.viewDidLoad()
        tableView.dataSource = self

        for i in 0..<db.count {
            let model = ProductModel.
            productsArray.append(model)
        }


        searchController.searchResultsUpdater = self
        searchController.searchBar.placeholder = "Поиск по продуктам..."
        tableView.tableHeaderView = searchController.searchBar // добавляет searchBar как заголовочный элемент в таблице, а не как одну из ячеек в tableView. (Встраивает панель поиска в панель навигации и позволяет её в принципе отображать на экране)
        searchController.obscuresBackgroundDuringPresentation = false //Требует не скрывать и оставлять активным tableView при использовании searchBar
        definesPresentationContext = true // отпускает строку поиска при смене экрана
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    // MARK: - Table view data source

//    override func numberOfSections(in tableView: UITableView) -> Int {
//        // #warning Incomplete implementation, return the number of sections
//        return 0
//    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if searchController.isActive && searchController.searchBar.text != "" {
            return searchResults.count
        } else { return productsArray.count }
    }

    var selectedProducts: String? = nil
    var selectedProductsIndex: Int? = nil
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: ProductModel = tableView.dequeueReusableCell(withIdentifier: "Product", for: indexPath) as! ProductModel
        
        if searchController.isActive && searchController.searchBar.text != "" {
            cell.textLabel?.text = searchResults[indexPath.row].name
        } else {
            cell.textLabel?.text = productsArray[indexPath.row].name
        }
        
        if indexPath.row == selectedProductsIndex {
            cell.accessoryType = .checkmark
        } else {
            cell.accessoryType = .none
        }
        
        return cell
    }
    
   override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {

    if tableView.cellForRow(at: indexPath)?.accessoryType == UITableViewCell.AccessoryType.detailDisclosureButton {
            tableView.cellForRow(at: indexPath)?.accessoryType = UITableViewCell.AccessoryType.none
            
        } else {
            tableView.cellForRow(at: indexPath)?.accessoryType = UITableViewCell.AccessoryType.detailDisclosureButton
            print(indexPath.row)
        print(productsArray[indexPath.row])
        }
    
    selectedProductsIndex = indexPath.row
    selectedProducts = productsArray[indexPath.row].name
    
    let cell = tableView.cellForRow(at: indexPath)
    cell?.accessoryType = .checkmark
    
    }

    
    
    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    /*override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }*/
    
}
 
