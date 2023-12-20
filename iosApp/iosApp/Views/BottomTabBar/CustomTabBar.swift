//
//  CustomTabBar.swift
//  iosApp
//
//  Created by tomasz.klecha on 19/12/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

enum Tabs: Int {
    case home = 0
    case workshops = 1
    case artists = 2
    case venues = 3
}

struct CustomTabBar: View {
    
    @Binding var selectedTab: Tabs
    
    var body: some View {
        HStack(alignment: .center)
        {
            
            // checking on files app - we prefer the change off main icon color, than tab above
            
            Button {
                selectedTab = .home
            } label: {
             
                TabBarButton(buttonText: "Home", imageName: "person", isActive: selectedTab == .home)
            }
            
            Button {
                selectedTab = .workshops
            } label: {
        
                TabBarButton(buttonText: "Workshops", imageName: "person", isActive: selectedTab == .workshops)
            }
            
            // If I need different action use tutorial https://www.youtube.com/watch?v=R_KZwX-yP4o
            
            Button {
                selectedTab = .artists
            } label: {
                TabBarButton(buttonText: "Artists", imageName: "person", isActive: selectedTab == .artists)
            }
            
            
            Button {
                selectedTab = .venues
            } label: {
                TabBarButton(buttonText: "Venue", imageName: "person", isActive: selectedTab == .venues)
            }
        }
        .frame(height: 82)
    }
}
