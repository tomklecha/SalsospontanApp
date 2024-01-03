//
//  TabBarButton.swift
//  iosApp
//
//  Created by tomasz.klecha on 20/12/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TabBarButton: View {
    
    var buttonText: String
    var imageName: String
    var isActive: Bool
    
    
    var body: some View {
        
        
        GeometryReader { geo in
            
            if isActive
            {
                
                Rectangle()
                    .foregroundColor(.blue)
                    .frame(width: geo.size.width,height: 4)
            }
            
            VStack(alignment: .center, spacing: 4){
                Image(systemName: imageName)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 24,height: 24)
                Text(buttonText)
            }.frame(width: geo.size.width, height: geo.size.height)
        }
    }
}
