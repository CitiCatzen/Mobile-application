//
//  Database.swift
//  RecipeSelector
//
//  Created by Артем on 24/12/2019.
//  Copyright © 2019 ArtemTkachuk. All rights reserved.
//

import Foundation
import SQLite

class Database {
 static let shared = Database()
    var ingredientsResult: [String] = []
    var recipesResult: [String] = []
    public var connection: Connection?
 init(){
 do {
    let dbPath = Bundle.main.path(forAuxiliaryExecutable: "/Users/artem/Documents/GitHub/Mobile-application/RecipeSelector/RecipeSelector/recipes.db")!
    connection = try Connection(dbPath)
    
    let ingredients = Table("ingredients")
    
    let ingredientId = Expression<Int64>("ingredient_id")
    let ingredientName = Expression<String>("ingredient")
    
    for ingredient in try (connection?.prepare(ingredients.select(ingredientName)))! {
        ingredientsResult += [ingredient[ingredientName]]
    }
    
    let recipes = Table("recipes")
    
    let dishName = Expression<String>("dish")
    let recipe = Expression<String>("recipe")
 
    for dish in try  (connection?.prepare(recipes.select(dishName)))! {
        recipesResult += [dish[dishName]]
    }
 } catch {
 connection = nil
 let nserror = error as NSError
 print ("Cannot connect to Database. Error is: \(nserror), \(nserror.userInfo)")
 }
 }
 }
