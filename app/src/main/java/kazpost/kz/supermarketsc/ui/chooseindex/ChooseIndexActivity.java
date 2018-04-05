package kazpost.kz.supermarketsc.ui.chooseindex;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kazpost.kz.supermarketsc.App;
import kazpost.kz.supermarketsc.R;
import kazpost.kz.supermarketsc.ui.scan.ScanViewModel;
import kazpost.kz.supermarketsc.ui.scan.ScanViewModelFactory;
import kazpost.kz.supermarketsc.utils.CommonUtils;
import kazpost.kz.supermarketsc.utils.ToastMessage;

import static kazpost.kz.supermarketsc.utils.CommonUtils.isMarketIndex;

public class ChooseIndexActivity extends AppCompatActivity {

    @BindView(R.id.et_market_index)
    EditText etMarketIndex;

    @Inject
    ScanViewModelFactory factory;

    private ScanViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_index);
        ButterKnife.bind(this);

        App.getApp().getmDiComponent().inject(this);

        mViewModel = ViewModelProviders.of(this, factory).get(ScanViewModel.class);

        if (mViewModel.isMarketIndexExist()) {
            etMarketIndex.setText(mViewModel.getMarketIndex());
        }

        mViewModel.getmToast().observe(this, (ToastMessage.ToastObserver) message -> {
            showToast(message);
            super.onBackPressed();
        });
    }


    @OnClick({R.id.floatingActionButton, R.id.btn_save_index})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.floatingActionButton:
                onBackPressedLogic();

                break;
            case R.id.btn_save_index:
                if (isMarketIndex(etMarketIndex.getText().toString())) {
                    mViewModel.saveMarketIndex(etMarketIndex.getText().toString());
                } else {
                    showToast("Неверно введен индекс");
                }
                break;
        }
    }

    private void onBackPressedLogic() {
        if (mViewModel.isMarketIndexExist()) {
            super.onBackPressed();
        } else {
            showToast("Не введен индекс");
        }

    }

    @Override
    public void onBackPressed() {
        onBackPressedLogic();
    }

    private void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 50);

        //trying to increase the toast font
        ViewGroup group = (ViewGroup) toast.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(20);

        toast.setText(msg);
        toast.show();
    }
}
