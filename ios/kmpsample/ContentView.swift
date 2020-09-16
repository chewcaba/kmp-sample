//
//  ContentView.swift
//  kmpsample
//
//  Created by A.J. Cabanatuan on 8/27/20.
//  Copyright © 2020 A.J. Cabanatuan. All rights reserved.
//

import SwiftUI
import SharedFramework

struct ContentView: View {
    
    @State private var username = ""
    @State private var password = ""
    @State private var showingAlert = false
    @State private var isLoginSuccessfulText = "false" // can't use bool with string interpolation?
    
    var loginService = LoginServiceFactory().makeInstance()
    
    var body: some View {
        VStack() {
            Spacer()
            Text("Welcome, Please Login")
                .foregroundColor(Color.white)
            TextField("Username", text: self.$username)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding(.horizontal, 20)
                .padding(.vertical, 5)
            TextField("Password", text: self.$password)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding(.horizontal, 20)
                .padding(.vertical, 5)
            Button(action: {
                var isLoginSuccessful = false
                do {
                    let login = try self.loginService.login(username: self.username, password: self.password)
                    if let result = login as? ResultSuccess {
                        isLoginSuccessful = result.data as! Bool
                    } else {
                        isLoginSuccessful = false
                    }
                } catch {
                    isLoginSuccessful = false
                }
                self.isLoginSuccessfulText = isLoginSuccessful
                    ? "true"
                    : "false"
                self.showingAlert = true
            }, label: {
                Text("Login")
            })
            .frame(minWidth: 0, maxWidth: .infinity)
                .padding(10)
                .background(Color.green)
                .foregroundColor(Color.white)
                .cornerRadius(30)
                .padding(.horizontal, 20)
            Spacer()
            .alert(isPresented: $showingAlert) {
                Alert(title: Text("Alert"), message: Text("Was Login Successful? \(isLoginSuccessfulText)"), dismissButton: .default(Text("Got it!")))
            }
        }.frame(minWidth: 0,
                maxWidth: .infinity,
                minHeight: 0,
                maxHeight: .infinity,
                alignment: .topLeading
        ).background(Color.purple)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
