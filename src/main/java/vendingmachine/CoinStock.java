package vendingmachine;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;

public class CoinStock {
    private Message message = new Message();
    private Parser parser = new Parser();
    private LinkedHashMap<Integer, Integer> coinCountMap = new LinkedHashMap<>();
    private List<Integer> coinAmountList = new ArrayList<>();

    CoinStock() {
        initCoinAmount();
        initCoinCount();
    }

    public void makeCoins(int holding) {
        while (true) {
            int selected = Randoms.pickNumberInList(coinAmountList);
            addCoin(selected);
            int sum = getSum();
            if (isSame(sum, holding)) {
                String output = parser.parseHolding(coinCountMap);
                message.printCoinCount(output);
                break;
            } else if (sum > holding) {
                initCoinCount();
            }
        }
    }

    public LinkedHashMap<Integer, Integer> getLastChanges(int changes) {
        LinkedHashMap<Integer, Integer> result = new LinkedHashMap<>();
        int remain = changes;
        for (int amount : coinAmountList) {
            int count = coinCountMap.get(amount);
            if (count > 0 && remain > 0 && remain % amount == 0) {
                int usedCount = getUsedCoinCount(amount, count, remain);
                remain -= usedCount * amount;
                result.put(amount, usedCount);
            }
        }
        return result;
    }

    private int getUsedCoinCount(int amount, int count, int remain) {
        if (remain / amount >= count) {
            return count;
        }
        return remain / amount;
    }

    private void initCoinAmount() {
        for (Coin coin : Coin.values()) {
            coinAmountList.add(coin.getAmount());
        }
    }

    private void initCoinCount() {
        coinCountMap.clear();
        for (int key : coinAmountList) {
            coinCountMap.put(key, 0);
        }
    }

    private boolean isSame(int sum, int holding) {
        if (sum == holding) {
            return true;
        }
        return false;
    }

    private void addCoin(int value) {
        coinCountMap.put(value, coinCountMap.get(value) + 1);
    }

    private int getSum() {
        int result = 0;
        for (int key : coinCountMap.keySet()) {
            result += key * coinCountMap.get(key);
        }
        return result;
    }
}
