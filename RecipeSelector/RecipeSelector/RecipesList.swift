//
//  RecipesList.swift
//  RecipeSelector
//
//  Created by Артем on 16/10/2019.
//  Copyright © 2019 ArtemTkachuk. All rights reserved.
//

import UIKit

class RecipesList: UITableViewController, UISearchResultsUpdating {
    
    private let searchController = UISearchController(searchResultsController: nil)
    private var recipesArray: [String] = ["LOL", "Kek", "cheburek"]
    private var searchResults = [String]()
        
    private func filterRecipes(for searchText: String) {
           searchResults = recipesArray.filter({ (recipe: String) -> Bool in
               return recipe.lowercased().contains(searchText.lowercased())
           })
           tableView.reloadData()
       }
    
    private var searchBarIsEmpty: Bool {
        guard let text = searchController.searchBar.text else { return false }
        return text.isEmpty
    }
    
    private var isFiltering: Bool {
        return searchController.isActive && !searchBarIsEmpty
    }
    
    func updateSearchResults(for searchController: UISearchController) {
        filterRecipes(for: searchController.searchBar.text ?? "")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.dataSource = self

        searchController.searchResultsUpdater = self
        tableView.tableHeaderView = searchController.searchBar
        searchController.obscuresBackgroundDuringPresentation = false
        searchController.searchBar.placeholder = "Поиск по рецептам..."
        definesPresentationContext = true
    }

    // MARK: - Table view data source


    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
         if searchController.isActive && searchController.searchBar.text != "" {
                   return searchResults.count
               } else { return recipesArray.count }
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Recipe", for: indexPath)
        
        if searchController.isActive && searchController.searchBar.text != "" {
            cell.textLabel?.text = searchResults[indexPath.row]
        } else {
            cell.textLabel?.text = recipesArray[indexPath.row]
        }
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        
        /*let data = recipesArray[indexPath.row]
        let destinationVC = FullRecipe()
        destinationVC.verificationId = data
        destinationVC.performSegue(withIdentifier: "showRecipeSegue", sender: self)
        */
        /*let row = indexPath.row
          print("Row: \(row)")
        let storyboard = UIStoryboard(name: "Main", bundle: Bundle.main) // Создаем константу с нашим Storyboard.
        let destination = storyboard.instantiateViewController(withIdentifier: "FullRecipe") // Создаём направление (где FullRecipe - Storyboard ID).

        if(row == 1) {
            
                    navigationController?.pushViewController(destination, animated: false) // pushViewController - то, без чего решения бы небыло. destination (в скобках) - направление которое мы ранее указывали).
                }*/
    }
    
    // MARK: - Navigation
    
   override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
    if segue.identifier == "showRecipeSegue" {
            if let indexPath = self.tableView.indexPathForSelectedRow {
                let verificationId: String
                
                if isFiltering {
                    verificationId = self.searchResults[indexPath.row]
                } else {
                    verificationId = self.recipesArray[indexPath.row]
                }
                let destinationVC = segue.destination as! FullRecipe
                destinationVC.verificationId = verificationId
            }
        }
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

}


