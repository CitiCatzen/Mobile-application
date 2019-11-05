//
//  FullRecipe.swift
//  RecipeSelector
//
//  Created by Артем on 28/10/2019.
//  Copyright © 2019 ArtemTkachuk. All rights reserved.
//

import UIKit

class FullRecipe: UIViewController {
    
    var verificationId: String?
    
    
    @IBOutlet weak var Label: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        Label.text = verificationId
        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
