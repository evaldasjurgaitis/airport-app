const { addLessLoader, override, addWebpackAlias } = require('customize-cra');
const StylelintPlugin = require('stylelint-webpack-plugin');
const path = require('path');

const addStylelint = (config, end) => {
    config.push(
        new StylelintPlugin({
            configFile: '.stylelintrc',
            context: 'src',
            files: '**/*.scss',
            failOnError: false,
            quiet: false,
            emitError: true,
        })
    );
    return config;
};
module.exports = override(
    addStylelint,
    addLessLoader({
        lessOptions: {
            javaEnabled: true,
            strictMatch: false,
        },
    }),
    addWebpackAlias({
        styles: path.resolve(__dirname, 'src/assets/styles'),
        src: path.resolve(__dirname, 'src'),
    })
);
