/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author sarah
 */
public class CardPile {
    private List<Card> cards;

    public CardPile() {
        cards = new ArrayList<>();
    }
    
    public void setNewCards(){
        cards.add(new CardOne());
        cards.add(new CardTwo());
        cards.add(new CardThree());
        cards.add(new CardFour());
        cards.add(new CardFive());
        cards.add(new CardSix());
        cards.add(new CardSeven());     
    }
    
    public void shuffleCards(){
        Collections.shuffle(cards);
    }
    
    public Card getCard(int index){
        return cards.get(index);
    }
    
    /**
     * Transfere uma carta para outro baralho (deck -> discarded e vice-versa)
     * @param index índice da carta no ArrayList
     * @return A carta de índice index que vai ser transferida para outro deck
     */
    public Card transferCard(int index){
        return cards.remove(index);
    }
    
    public void receiveCard(Card c){
        cards.add(c);
    }
    
    /**
     * Obtém a quantidade de cartas que existem na pilha
     * @return tamanho do ArrayList das cartas desta pilha
     */
    public int getCardPileSize(){
        return cards.size();
    }
    
    
}
