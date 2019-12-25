//
//  ProductModel.swift
//  RecipeSelector
//
//  Created by Артем on 18/10/2019.
//  Copyright © 2019 ArtemTkachuk. All rights reserved.
//

import Foundation
import UIKit

class ProductModel: UITableViewCell {
    var name: String
    var select: String = "Выбранно"
    static var selectBool: Bool = false
    
    var massageView: UITextView = {
        var textView = UITextView()
        return textView
    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
